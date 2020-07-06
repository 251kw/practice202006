package util;

public class CheckStr {

	/**
	 * String型の情報をsqlに変換するメソッド
	 * @param str 情報
	 * @return sql文か"no"
	 */
	public static String Check(String str) {
		if(str != null && str.isEmpty() != true) {
			str =" LIKE '%"+str+"%'";
		}else {
			str = "no";
		}

		return str;
	}

	/**
	 * 配列型の情報をString型のsqlに変換するメソッド
	 * @param icon アイコン情報(今のところアイコンしかないため)
	 * @return sql文か"no"
	 */
	public static String CheckList(String[] icon) {
		String str = null;
		int i;
		if(icon==null || icon.length ==0) {
			str = "no";
		}else {
			str = " IN (";
			for(i=0; i<icon.length; i++) {
				str =str+"'"+icon[i]+"',";
			}
			str = str.substring(0, (str.length() - 1));
			str = str+"))";
		}

		return str;
	}

}
