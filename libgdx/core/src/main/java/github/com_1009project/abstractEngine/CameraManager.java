package github.com_1009project.abstractEngine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class CameraManager {
    public OrthographicCamera camera = null;

    private final float scale = 1.0f;

    public CameraManager(int width, int height) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width / scale, height / scale);
    }

    public void cameraUpdate(float delta, Vector3 position) {
        camera.position.set(position);
        camera.update();
    }
}
