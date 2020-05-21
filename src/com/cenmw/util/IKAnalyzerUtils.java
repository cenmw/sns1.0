package com.cenmw.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.wltea.analyzer.dic.Dictionary;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class IKAnalyzerUtils {
	private static IKAnalyzerUtils iku;
	private static Analyzer analyzer;

	/**
	 * 自定义词列表
	 * 
	 * @param wordList
	 * @return IKAnalyzerUtils
	 */
	public static void init(List wordList) {
		if (analyzer == null) {
			if (wordList != null && !wordList.isEmpty()) {
				Dictionary d = Dictionary.getSingleton();
				d.addWords(wordList);
			}
			analyzer = new IKAnalyzer();
		}
	}

	public static List searchWords(String text) throws IOException {
		List list = new ArrayList();
		// IKSegmentation ik = new IKSegmentation(new StringReader(text), true);
		// Lexeme lexeme = null;
		// while ((lexeme = ik.next()) != null) {
		// String word = lexeme.getLexemeText();
		// list.add(word);
		// }

		/********************************************/
		TokenStream tokenStream = analyzer.tokenStream("text",
				new StringReader(text));
		tokenStream.addAttribute(TermAttribute.class);
		while (tokenStream.incrementToken()) {
			TermAttribute termAttribute = (TermAttribute) tokenStream
					.getAttribute(TermAttribute.class);
			String word = termAttribute.term();
			list.add(word);
		}

		return list;
	}
}
