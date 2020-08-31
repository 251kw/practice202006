package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dto.ShoutDTO;
import dto.UserDTO;

public class DataManager {
	private ArrayList<UserDTO> userList;	// 登録ユーザ情報リスト
	private ArrayList<ShoutDTO> shoutList;	// 書き込み内容リスト

	// ログインID とパスワードを受け取り、登録ユーザ一覧に一致したものがあるか検索
	public UserDTO getLoginUser(String loginId, String password) {
		UserDTO user = null;

		for(UserDTO u : userList){
			if(u.getLoginId().equals(loginId) && u.getPassword().equals(password)){
				// 一致したものがあれば、そのユーザ情報の参照を戻す
				user = u;
			}
		}

		return user;
	}

	// 書き込み内容リストの getter
	public ArrayList<ShoutDTO> getShoutList() {
		return shoutList;
	}

	// ログインユーザ情報と書き込み内容を受け取り、リストに追加する
	public void setWriting(UserDTO user, String writing) {
		ShoutDTO s = new ShoutDTO();

		s.setUserName(user.getUserName());
		s.setIcon(user.getIcon());
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		s.setDate(sdf.format(calender.getTime()));
		s.setWriting(writing);

		shoutList.add(0, s);
	}
}
