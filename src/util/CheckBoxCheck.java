package util;

/**
 * @author d.ito
 *チェックボックスの値保持を判定するメソッド
 */
public class CheckBoxCheck {
	public static String boxCheck(String[] hId, String loginId) {
		String result = "";
		for (int i = 0; i <= hId.length - 1; i++) {
			if(hId[i].equals(loginId)) {
				result = "checked = \"checked\" ";
			}
		}
		return result;
	}
}
