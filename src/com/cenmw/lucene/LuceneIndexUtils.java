package com.cenmw.lucene;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.cenmw.lucene.inter.SearchEnabled;
import com.cenmw.util.RootdirectoryPopUtil;

public class LuceneIndexUtils {
	private static final String DATE_FORMAT = "yyyyMMddHHmmssSSS";// 默认日期格式
	private static final Analyzer analyzer = new IKAnalyzer();
	protected static final String _KEYWORD_FIELD_NAME = "id";
	/*
	 * 用户搜索关键字字段名称 例如:值存为 内容的标题和关键字和描述
	 */
	public static final String SEARCH_KEYWORD_FIELDNAME = "SEARCH_KEYWORD_FIELDNAME";

	/**
	 * 从索引库中搜索
	 * 
	 * @param beanClass
	 * @param query
	 * @param max_count
	 * @return
	 * @throws IOException
	 */
	public static List<Integer> find(Class<? extends SearchEnabled> beanClass,
			Query query, int max_count) throws IOException {
		IndexSearcher searcher = _GetSearcher(beanClass);
		try {
			TopDocs hits = searcher.search(query, null, max_count);
			if (hits == null)
				return null;
			List<Integer> results = new ArrayList<Integer>();
			int numResults = Math.min(hits.totalHits, max_count);
			for (int i = 0; i < numResults; i++) {
				ScoreDoc s_doc = (ScoreDoc) hits.scoreDocs[i];
				Document doc = searcher.doc(s_doc.doc);
				int id = NumberUtils.toInt(doc.get(_KEYWORD_FIELD_NAME), 0);
				if (id > 0 && !results.contains(id))
					results.add(id);
			}
			return results;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			searcher.close();
		}
	}
	
	/**
	 * 删除文档
	 * 
	 * @param doc
	 * @throws IOException
	 */
	public static void delete(SearchEnabled doc) throws IOException {
		if (doc == null)
			return;
		IndexWriter writer = _GetWriter(doc.getClass());
		try {
			writer.deleteDocuments(new Term("id", String.valueOf(doc.getId())));
			writer.commit();
		} finally {
			writer.close();
		}
	}

	/**
	 * 更新文档
	 * 
	 * @param doc
	 * @throws Exception
	 */
	public static void update(SearchEnabled doc) throws Exception {
		if (doc == null)
			return;
		IndexWriter writer = _GetWriter(doc.getClass());
		try {
			writer.deleteDocuments(new Term("id", String.valueOf(doc.getId())));
			writer.addDocument(_ObjectToDocument(doc));
			writer.commit();
		} finally {
			writer.close();
		}
	}

	/**
	 * 添加文档
	 * 
	 * @param doc
	 * @throws Exception
	 */
	public static void add(SearchEnabled doc) throws Exception {
		if (doc == null)
			return;
		IndexWriter writer = _GetWriter(doc.getClass());
		try {
			writer.addDocument(_ObjectToDocument(doc));
			writer.commit();
		} finally {
			writer.close();
		}
	}

	/**
	 * 添加文档
	 * 
	 * @param objClass
	 * @param doc
	 * @throws Exception
	 */
	public static int add(Class<? extends SearchEnabled> objClass,
			List<? extends SearchEnabled> docs) throws Exception {
		if (docs == null || docs.size() == 0)
			return 0;
		IndexWriter writer = _GetWriter(objClass);
		try {
			int ar = _Add(writer, docs);
			writer.optimize();
			return ar;
		} finally {
			writer.close();
			writer = null;
		}
	}

	/**
	 * 添加文档
	 * 
	 * @param doc
	 * @throws Exception
	 */
	private static int _Add(IndexWriter writer,
			List<? extends SearchEnabled> docs) throws Exception {
		if (docs == null || docs.size() == 0)
			return 0;
		int doc_count = 0;
		for (SearchEnabled doc : docs) {
			Document lucene_doc = _ObjectToDocument(doc);
			lucene_doc.setBoost(doc.GetBoost());
			writer.addDocument(lucene_doc);
			doc_count++;
		}
		return doc_count;
	}

	private static Document _ObjectToDocument(SearchEnabled doc)
			throws Exception {
		Document lucene_doc = new Document();
		// Set keyword field
		lucene_doc.add(_Keyword(_KEYWORD_FIELD_NAME, "" + doc.getId()));
		// Set storage field
		String[] storeFields = doc.GetStoreFields();
		if (storeFields != null)
			for (String s_field : storeFields) {
				String propertyValue = _GetField(doc, s_field);
				if (propertyValue != null)
					lucene_doc.add(_Keyword(s_field, propertyValue));
			}
		// Set extends values
		if (doc.GetExtendValues() != null) {
			for (String key : doc.GetExtendValues().keySet()) {
				String value = doc.GetExtendValues().get(key);
				lucene_doc.add(_Keyword(key, value));
			}
		}
		// Set indexed field
		String[] indexFields = doc.GetIndexFields();
		for (String idx_field : indexFields) {
			String propertyValue = _GetField(doc, idx_field);
			if (StringUtils.isNotBlank(propertyValue))
				lucene_doc.add(_Index(idx_field, propertyValue));
		}

		// Set extends values
		if (doc.GetExtendIndexValues() != null) {
			for (String key : doc.GetExtendIndexValues().keySet()) {
				String value = doc.GetExtendIndexValues().get(key);
				try {
					lucene_doc.add(_Index(key, value));
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		// SearchFieldValue
		if (doc.getSearchFieldValue() != null) {
			lucene_doc.add(_Index(SEARCH_KEYWORD_FIELDNAME,
					doc.getSearchFieldValue()));
		}
		return lucene_doc;
	}

	/**
	 * 访问对象某个属性的值
	 * 
	 * @param obj
	 * @param field
	 * @return
	 */
	private static String _GetField(Object obj, String field) throws Exception {
		Object fieldValue = PropertyUtils.getProperty(obj, field);
		if (fieldValue == null) {
			return "";
		}
		if (fieldValue instanceof String)
			return (String) fieldValue;
		if (fieldValue instanceof Date)
			return DateFormatUtils.format((Date) fieldValue, DATE_FORMAT);
		return String.valueOf(fieldValue);
	}

	/**
	 * Field.Index.NOT_ANALYZED:不分词且索引 Field.Store.YES:存储字段值（未分词前的字段值）
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	private static final Field _Keyword(String name, String value) {
		return new Field(name, value, Field.Store.YES, Field.Index.NOT_ANALYZED);
	}

	/**
	 * Field.Index.ANALYZED:分词建索引 Field.Store.NO:不存储,存储与索引没有关系
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	private static final Field _Index(String name, String value) {
		return new Field(name, value, Field.Store.NO, Field.Index.ANALYZED);
	}

	/**
	 * 获取索引写
	 * 
	 * @param beanClass
	 * @return
	 * @throws IOException
	 */
	protected static IndexWriter _GetWriter(Class<?> beanClass)
			throws IOException {
		Directory indexDir = FSDirectory.open(new File(
				RootdirectoryPopUtil.rootdirectorys.get("luceneBaseDir")
						+ beanClass.getSimpleName()));
		return new IndexWriter(indexDir, analyzer,
				IndexWriter.MaxFieldLength.UNLIMITED);
	}

	/**
	 * 获取索引读
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	protected static IndexSearcher _GetSearcher(Class<?> beanClass)
			throws IOException {
		Directory indexDir = FSDirectory.open(new File(
				RootdirectoryPopUtil.rootdirectorys.get("luceneBaseDir")
						+ beanClass.getSimpleName()));
		@SuppressWarnings("deprecation")
		IndexSearcher is = new IndexSearcher(indexDir);
		return is;
	}

	public final static Analyzer getAnalyzer() {
		return analyzer;
	}

}
