package org.b3log.xiaov.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserUtils {

	public static final String USER_ID = "userId";

	public static final String QUOTES = "quotes";

	public static final String USER_NAME = "userName";

	public static final Map<Long, Map<String, List<String>>> quotes = new HashMap<>();

	static {
		try {
			ObjectMapper mapper = new ObjectMapper();

			JSONObject jsonObject = parseJSONFile(SpecReactor.class.getResource("/quotes.json").getPath());
			JSONArray data = jsonObject.getJSONArray("data");

			for (int i = 0; i < data.length(); i++) {
				Map<String, List<String>> quote = new HashMap<>();
				quote.put(data.getJSONObject(i).getString(USER_NAME), mapper.readValue(
						data.getJSONObject(i).getJSONArray(QUOTES).toString(), new TypeReference<List<String>>() {
						}));
				quotes.put(data.getJSONObject(i).getLong(USER_ID), quote);
			}

		} catch (IOException | JSONException e) {
			throw new RuntimeException("Can not read quote file,", e);
		}
	}

	private static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
		String content = new String(Files.readAllBytes(Paths.get(filename)));
		return new JSONObject(content);
	}
}
