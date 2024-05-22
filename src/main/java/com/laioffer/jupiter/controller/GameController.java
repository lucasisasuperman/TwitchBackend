package com.laioffer.jupiter.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.jupiter.service.GameService;
import com.laioffer.jupiter.service.TwitchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.server.ServerCloneException;

@Controller //一定要加controller确保api能用，定义一个方法，处理特定的url请求
public class GameController {
    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/game", method = RequestMethod.GET) //get方法找到controller，value=‘/game’过来找我，找到对应的controller
    public void getGame(@RequestParam(value = "game_name", required = false) String gameName, HttpServletResponse response) //url上没有/game就返回404
            throws IOException, ServletException { //required可以不写，如果不写，url里没有gamename的话会返回404
        response.setContentType("application/json;charset=UTF-8");
        try {
            // Return the dedicated game information if gameName is provided in the request URL, otherwise return the top x games.
            if (gameName != null) {
                response.getWriter().print(new ObjectMapper().writeValueAsString(gameService.searchGame(gameName)));
            } else {
                response.getWriter().print(new ObjectMapper().writeValueAsString(gameService.topGames(0)));
            }
        } catch (TwitchException e) {
            throw new ServletException(e);
        }
    }
}
