package github.com_1009project.lwjgl3;

public class UILayer extends Layer {
    private UIManager uiManager;

    public UILayer() {
        uiManager = new UIManager();
    }

    @Override
    public void update(float deltaTime) {
        uiManager.update(deltaTime);
    }

    @Override
    public void render() {
        //i dont think this is needed
    }

    @Override
    public void dispose() {
        uiManager.dispose();
    }
    // optional can remove if not needed
    public UIManager getUIManager() {
        return uiManager;
    }
}
