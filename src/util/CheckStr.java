package util;

public class CheckStr {


	public String Check(String str) {
		if(str != null && str.isEmpty() != true) {
			str =" LIKE '%"+str+"%'";
		}else {
			str = "no";
		}

		return str;
	}

}
