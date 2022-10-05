package step3.domain;

import step3.view.ResultView;

public class Play {

    private static final ResultView RESULT_VIEW = new ResultView();
    private static final RandomNumber RANDOM_NUMBER = new RandomNumber();

    public void playGame(GameInfo gameInfo) {
        for (int i = 1; i <= gameInfo.getCount(); i++) {
            run(gameInfo);
            countCheck(gameInfo, i);
        }
    }

    private void countCheck(GameInfo gameInfo, int loopPoint) {
        if (loopPoint == gameInfo.getCount()) {
            isLastGame(gameInfo);
        }
    }

    private void run(GameInfo gameInfo) {
        for (Car car : gameInfo.getCarList()) {
            car.moveCheck(RANDOM_NUMBER.randomNumber());
        }
        RESULT_VIEW.racingOutput(gameInfo);
    }

    private void isLastGame(GameInfo gameInfo) {
        RESULT_VIEW.winnerOutput(new Winner(gameInfo));
    }
}
