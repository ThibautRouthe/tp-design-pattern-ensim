package fr.ensim.dp.map.player;

public class StatePlay implements IStatePlayer{
    @Override
    public void play(IPlayer player) {
        player.firechangeState(new StatePlay());
    }

    @Override
    public void stop(IPlayer player) {
        throw new IllegalStateException();
    }

    @Override
    public void pause(IPlayer player) {
        throw new IllegalStateException();
    }

    @Override
    public void forward(IPlayer player) {
        throw new IllegalStateException();
    }

    @Override
    public void backward(IPlayer player) {
        throw new IllegalStateException();
    }
}
