function parseJSON(data) {
	return (new Function(" return " + data))();
}