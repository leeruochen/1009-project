package github.com_1009project.lwjgl3;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class UILayer extends Layer {
    private Stage stage;

    public UILayer() {
        new Stage(new ScreenViewport());
    }

    @Override
    public void update(float deltaTime) {
        stage.act(deltaTime);
    }

    @Override
    public void render() {
       stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public void addActor(Actor actor){
        stage.addActor(actor);
    }
    // optional can remove if not needed
    public Stage getUIManager() {
        return stage;
    }
}
