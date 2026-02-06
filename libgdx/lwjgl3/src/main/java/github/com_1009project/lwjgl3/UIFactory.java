package github.com_1009project.lwjgl3;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class UIFactory {
    private Skin skin;

    public UIFactory(Skin skin) {
        this.skin = skin;
    }

    public TextButton createButton(String text, final Runnable onClick) {
        // Create button using the "default" style in the Skin
        TextButton btn = new TextButton(text, skin);
        
        // Add interaction logic
        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onClick.run(); // Execute the code passed in
            }
        });
        
        return btn;
    }

    //TODO: Add resource bars that tie into the resource manager
}
