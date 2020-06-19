package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dto.ShoutDTO;
import dto.UserDTO;

public class DataManager {
	private ArrayList<UserDTO> userList;
	private ArrayList<ShoutDTO> shoutList;

	public DataManager() {
		// 登録ユーザー情報を生成しリストに追加
		userList = new ArrayList<UserDTO>();
		UserDTO udto;
		udto = new UserDTO ("yamada","pass1","山田 太郎","icon-user","はじめまして");
		userList.add(udto);
		udto = new UserDTO ("suzuki","pass2","鈴木 花子","icon-user-female","東京在住です");
		userList.add(udto);
		udto = new UserDTO("itou","pass3","伊藤 恵","icon-bell","趣味は読書");
		userList.add(udto);
		shoutList = new ArrayList<ShoutDTO>();

		// 書き込み情報を生成し、リストに追加
		ShoutDTO stdo;
		stdo = new ShoutDTO("テスト","icon-rocket"," 2017-01-02 12:34:56","こんばんわ");
		shoutList.add(stdo);
	}

	//ログインIDとパスワードを受け取り、登録ユーザー一覧に一致したものがあるか検索
	public UserDTO getloginUser(String loginId,String password) {
		UserDTO user = null;

		for(UserDTO u : userList) {
			if(u.getLoginId().equals(loginId) && u.getPassword().equals(password)) {
				// 一致したものがあれば、そのユーザの参照を戻す
				user = u;
			}
		}

		return user;
	}

	// 書き込み内容リストのgetter
	public ArrayList<ShoutDTO> getShoutList(){
		return shoutList;
	}

	// ログインユーザ情報と書き込み内容を受け取り、リストに追加する
	public void setWriting(UserDTO user, String writing) {
		ShoutDTO s = new ShoutDTO();

		s.setUserName(user.getUserName());
		s.setIcon(user.getIcon());
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		s.setData(sdf.format(calendar.getTime()));
		s.setWriting(writing);

		shoutList.add(0, s);

	}
}
