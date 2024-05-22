package com.laioffer.jupiter.service;

public class TwitchException extends RuntimeException {
    public TwitchException(String errorMessage) {
        super(errorMessage);
    }

    public class GameService {
        private static final String TOKEN = "Bearer 6av0akiuvcnkykdo831oog7u8z3z1w";
        private static final String CLIENT_ID = "71kycwrrigd3fkfnac1wnw1zfoq0bh";
        private static final String TOP_GAME_URL = "https://api.twitch.tv/helix/games/top?first=%s";
        private static final String GAME_SEARCH_URL_TEMPLATE = "https://api.twitch.tv/helix/games?name=%s";
        private static final int DEFAULT_GAME_LIMIT = 20;
    }

}

