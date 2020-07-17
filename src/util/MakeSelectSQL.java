package util;

public class MakeSelectSQL {

	/**
	 * 引数にloginID,userName,profile,icon-car,icon-clip,icon-radioを渡すと
	 * 渡されたものが空文字の場合無視し、引数に値があるものだけを条件に加えた
	 * SQL文を生成するメソッド
	 * @param loginID ログインID
	 * @param userName ユーザー名
	 * @param profile プロフィール
	 * @param icon_car 車のアイコンの変数名
	 * @param icon_clip クリップのアイコンの変数名
	 * @param icon_radio ラジオのアイコンの変数名
	 * @return
	 */
	public static String makeSelect(String loginID, String userName, String profile, String icon_car,String icon_clip,String icon_radio) {
		String select = "SELECT * FROM users ORDER BY loginId;";

		// 記入されている項目を数えて、判断する
		int judg = 0;
		// icon の選択数を数える
		int judg_icon = 0;

		//SELECT文の一部を入れる
		String log = "";
		String name = "";
		String pro = "";
		String icon = "";

		//二つ以上の項目が検索された時の使う
		String item1 = "";
		String item2 = "";
		String item3 = "";
		String item4 = "";

		//各項目が入力されているかチェックし、部分的にSELECT文を作る
		if(!loginID.equals("")) {
			log = ("loginId LIKE '%" + loginID + "%'" );
			judg++;

			item1 = log;

		}

		if(!userName.equals("")) {
			name = ("userName LIKE '%" + userName + "%'" );
			judg++;

			//上で使われていなかった場合はitem1にnameが入る
			if(item1.equals("")) {
				item1 = name;
			}else {
				item2 = name;
			}

		}

		if(!profile.equals("")) {
			pro = ("profile LIKE '%" + profile + "%'" );
			judg++;

			if(item1.equals("")) {
				item1 = pro;
			}else if(item2.equals("")) {
				item2 = pro;
			}else {
				item3 = pro;
			}

		}

		//アイコンのSELECT文を作る
		if(!icon_car.equals("") || !icon_clip.equals("") || !icon_radio.equals("")) {
			judg++;

			String icon1 = "";
			String icon2 = "";
			String icon3 = "";

			if(!icon_car.equals("")) {
				judg_icon++;
				icon1 = icon_car;
			}
			if(!icon_clip.equals("")) {
				judg_icon++;

				if(icon1.equals("")) {
					icon1 = icon_clip;
				}else {
					icon2 = icon_clip;
				}
			}
			if(!icon_radio.equals("")) {
				judg_icon++;
				if(icon1.equals("")) {
					icon1 = icon_radio;
				}else if(icon2.equals("")) {
					icon2 = icon_radio;
				}else {
					icon3 = icon_radio;
				}
			}


			// judg_icon の数に応じてアイコンのSELECT文を作る
			if(judg_icon == 1) {
				icon = ("icon='" + icon_car + icon_clip + icon_radio + "'");
			}else if(judg_icon == 2) {
				icon = ("( icon='" + icon1 + "' OR icon='" + icon2 + "' )");
			}else {
				icon = ("( icon='" + icon1 + "' OR icon='" + icon2 + "' OR icon='" + icon3 + "' )");
			}


			if(item1.equals("")) {
				item1 = icon;
			}else if(item2.equals("")) {
				item2 = icon;
			}else if(item3.equals("")){
				item3 = icon;
			}else {
				item4 = icon;
			}

		}

		//judgの数に応じて全体のセレクト文を作る
		if(judg == 0) {
			//NOP
		}else if(judg == 1){
			select = ("SELECT * FROM users WHERE " + log + name + pro + icon + " ORDER BY loginId;");
		}else if(judg == 2) {
			select = ("SELECT * FROM users WHERE " + item1 + "AND" + item2 + " ORDER BY loginId;");
		}else if(judg == 3) {
			select = ("SELECT * FROM users WHERE " + item1 + "AND" + item2 + "AND" + item3 + " ORDER BY loginId;");
		}else if(judg == 4) {
			select = ("SELECT * FROM users WHERE " + item1 + "AND" + item2 + "AND" + item3 + "AND" + item4 + " ORDER BY loginId;");
		}else {
			//NOP
		}


		return select;
	}


	/**
	 * @param loginIds 取得したいユーザー情報群のログインIDの配列
	 * @return sql String型のSELECTのSQL文
	 */
	public static String makeSelects(String[] loginIds) {
		String sql ="";

		if(loginIds.length == 1) {
			sql = ("SELECT * FROM users WHERE loginId='" + loginIds[0] + "' AND d_flg=0;");
		}else {
			sql = ("SELECT * FROM users WHERE loginId='"+loginIds[0]+"'");
			for(int i =1 ;loginIds.length > i ; i++) {
				sql = (sql + " OR loginId='" +loginIds[i] + "'");
			}
			sql = (sql +"AND d_flg=0;");
		}

		return sql;
	}

	/**
	 * @param loginIds 削除したいユーザーのログインIDの配列
	 * @return sql String型のUPDETEのSQL文(user論理削除)
	 */
	public static String makeDeletes(String[] loginIds) {
		String sql ="";

		if(loginIds.length == 1) {
			sql = ("UPDATE users SET d_flg=1 WHERE loginId='" + loginIds[0] + "' AND d_flg=0;");
		}else {
			sql = ("UPDATE users SET d_flg=1 WHERE loginId='"+loginIds[0]+"'");
			for(int i =1 ;loginIds.length > i ; i++) {
				sql = (sql + " OR loginId='" +loginIds[i] + "'");
			}
			sql = (sql +" AND d_flg=0;");
		}

		return sql;
	}

	/**
	 * @param shoutsId 取得したいシャウトのシャウトID
	 * @return SELECTのSQL文
	 */
	public static String makeSelectsShouts(String shoutsId) {
		String sql ="";
		sql = ("SELECT shoutsId,userName,icon,date,writing,u.loginId FROM shouts s INNER JOIN users u ON s.loginid=u.loginId WHERE shoutsId='" + shoutsId + "' AND s.d_flg=0;");

		return sql;
	}

	/**
	 * @param shoutsId 取り出したいシャウトのshoutsId
	 * @return sql String型のSELECTのSQL文(shouts論理削除)
	 */
	public static String makeSelectsShouts(String[] shoutsId) {
		String sql ="";

		if(shoutsId.length == 1) {
			sql = ("SELECT shoutsId,userName,icon,date,writing,u.loginId FROM shouts s INNER JOIN users u ON s.loginid=u.loginId WHERE shoutsId='" + shoutsId[0] + "' AND s.d_flg=0;");
		}else {
			sql = ("SELECT shoutsId,userName,icon,date,writing,u.loginId FROM shouts s INNER JOIN users u ON s.loginid=u.loginId WHERE shoutsId='"+shoutsId[0]+"'");
			for(int i =1 ;shoutsId.length > i ; i++) {
				sql = (sql + " OR shoutsId='" +shoutsId[i] + "'");
			}
			sql = (sql +"AND s.d_flg=0;");
		}

		return sql;
	}

	/**
	 * @param loginIds 削除したいユーザーのログインIDの配列
	 * @return sql String型のUPDETEのSQL文
	 */
	public static String makeDeletesShoutslogIds(String[] loginIds) {
		String sql ="";

		if(loginIds.length == 1) {
			sql = ("UPDATE shouts SET d_flg=1 WHERE loginId='" + loginIds[0] + "' AND d_flg=0;");
		}else {
			sql = ("UPDATE shouts SET d_flg=1 WHERE loginId='"+loginIds[0]+"'");
			for(int i =1 ;loginIds.length > i ; i++) {
				sql = (sql + " OR loginId='" +loginIds[i] + "'");
			}
			sql = (sql +" AND d_flg=0;");
		}

		return sql;
	}

	/**
	 * @param shoutsId 削除したいシャウトのshoutsId
	 * @return sql String型のUPDATEのSQL文(shouts論理削除)
	 */
	public static String makeDeletesShoutsIds(String[] shoutsId) {
		String sql ="";

		if(shoutsId.length == 1) {
			sql = ("UPDATE shouts SET d_flg=1 WHERE shoutsId='" + shoutsId[0] + "' AND d_flg=0;");
		}else {
			sql = ("UPDATE shouts SET d_flg=1 WHERE shoutsId='"+shoutsId[0]+"'");
			for(int i =1 ;shoutsId.length > i ; i++) {
				sql = (sql + " OR shoutsId='" +shoutsId[i] + "'");
			}
			sql = (sql +" AND d_flg=0;");
		}

		return sql;
	}
}