package github.com_1009project.lwjgl3;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class UIManager {
    private Stage stage;
    private Skin skin;
    private Table rootTable;

    public UIManager() {
        // 1. Setup the Stage (The container for all UI)
        stage = new Stage(new ScreenViewport());

        // 2. Load Styles (Fonts, textures defined in a .json file)
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        // 3. Setup the Layout Root
        // 'Table' is like an HTML <table> or CSS Flexbox. It aligns things.
        rootTable = new Table();
        rootTable.setFillParent(true); // Make it fill the whole screen
        rootTable.top().left(); // Align content to Top-Left by default
        
        stage.addActor(rootTable);
    }

    public void update(float delta) {
        stage.act(delta); // Run animations (fades, slides)
        stage.draw();     // Draw to screen
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    // This is crucial for input!
    public Stage getStage() {
        return stage;
    }
    
    // Expose the Table so we can add things to it
    public Table getRootTable() {
        return rootTable;
    }
    
    // Expose the Skin so the Factory can use it
    public Skin getSkin() {
        return skin;
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
