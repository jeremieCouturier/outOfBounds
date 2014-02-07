package outofbounds

class Text {
	public static String summaryText(String text) {
		String summary = text.replaceAll(/<[^<>]*>/,"")
		if (summary.size() > 110) {
			summary = summary.substring(0,110)
		}
		return summary
	}
}
