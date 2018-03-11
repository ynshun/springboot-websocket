package com.ynshun.config.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ynshun.domain.UserInfo;

public class MemoryCache {
	private static Map<String, UserInfo> users = new HashMap<String, UserInfo>();
	
	private static String[] nicknames = new String[] {
		"我对你的爱丶唤来你的讨厌",
		"我沉默　对着一切熟视无睹",
		"是谁浮夸了你的青春年华?,",
		"′别用ゝ虚伪的脸颊对我笑",
		"你的心深处是否也有我的名",
		"能不能不爱了爱情它太痛了",
		"ー个不善于言语旳男乆╰︶",
		"烽火戏诸侯只为驳伊人一笑",
		"喂,那女孩嫁给我吧~",
		"原谅莪对迩旳伪冷漠-",
		"孓曰.尐孑亥孓嗼說愛",
		"我会认为你是个疯子 ぺ",
		"倒转流年、只为一眼红颜",
		"戓許、伱就昰莪啲下一任",
		"·潜意识里有你的存在·",
		"最后的最后, 人去楼空∝",
		"CL我希望能够绑架她的心",
		"还忘不了你给我叠的星星",
		"哥是你们模仿不了的神、",
		"。 曾经的曾经兜是曾经ゝ",
		"时间看清一个人 #",
		"轻描淡写你的温柔",
		"梦中人变朱砂痣゛",
		"没心没肺く小男人",
		"一哏煙，一斷情。",
		"人张啥样 全凭想像",
		"＂让人不安的浪荡",
		"偶、何时如此狼狈。",
		"回忆 、回不去",
		"又是一年春风吹",
		"日落等你回来。",
		"终于有人爱我了",
		"女人是用来疼的",
		"鹿胎、补血颗粒",
		"心只是飘忽不定",
		"假情假意假温柔.",
		"没有幸免没有重来",
		"♂伱褆俺哒↘",
		"第Ⅱ季度复Su",
		"你在我心底。",
		"專署式、悲傷"
	};
	
	private static String[] icons = new String[] {
			"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=688204059,3185740549&fm=27&gp=0.jpg",
			"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2143201117,1038640037&fm=27&gp=0.jpg",
			"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=613470200,2400246631&fm=27&gp=0.jpg",
			"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1517933727,1380720665&fm=27&gp=0.jpg",
			"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=685957570,2973009367&fm=27&gp=0.jpg",
			"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2866541886,4281566709&fm=27&gp=0.jpg",
			"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3444282039,2038294810&fm=27&gp=0.jpg",
			"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2922887545,2754541549&fm=27&gp=0.jpg",
			"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3090816975,2767337125&fm=27&gp=0.jpg",
			"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1548823784,3145444496&fm=27&gp=0.jpg",
			"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=183496190,3782019185&fm=27&gp=0.jpg",
			"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1188941175,1791106396&fm=27&gp=0.jpg",
			"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1310493670,364853435&fm=27&gp=0.jpg"
	};
	
	
	static {
		Random random = new Random();
		for (int i = 0; i < nicknames.length; i++) {
			addUser("user" + i, "123456", nicknames[i], icons[random.nextInt(13)]);
		}
	}
	
	
	public static List<UserInfo> getAllUser() {
		List<UserInfo> userList = new ArrayList<UserInfo>();

		for (String username : users.keySet()) {
			userList.add(users.get(username));
		}

		return userList;
	}

	public static UserInfo getUserInfo(String username) {
		return users.get(username);
	}

	public static boolean userExists(String username) {
		return users.containsKey(username);
	}

	public static boolean addUser(String username, String password, String nickname, String icon) {
		UserInfo user = new UserInfo();
		user.setUsername(username);
		user.setPassword(password);
		user.setNickname(nickname);
		user.setIcon(icon);

		users.put(username, user);

		return true;
	}

	public static boolean login(String username, String password) {
		if (StringUtils.isAnyBlank(username, password) || !userExists(username)) {
			return false;
		}
		return users.get(username).getPassword().equals(password);
	}
	
	
	public static boolean logined() {
		return currentUser() != null;
	}
	
	public static UserInfo currentUser() {
		return (UserInfo) getSession().getAttribute("currentUser");
	}
	
	public static void currentUser(UserInfo user) {
		getSession().setAttribute("currentUser", user);
	}
	
	
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static HttpServletResponse getResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	public static HttpSession getSession() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}

}
