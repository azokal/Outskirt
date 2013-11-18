package kr.ac.kmu.gameproject.outskirt;

public class Menu implements Screen {

	private App app;
	
	public Menu(App app) {
		this.app = app;
	}
	
	@Override
	public void draw() {
		app.background(255);
		app.textAlign(app.CENTER, app.TOP);
		app.fill(0);
		app.text("Press enter to start the game!", app.width / 2, app.height / 2);
		if (app.isPressed(App.ENTER)) {
			app.setScreen(new Game(app));
		}
	}

	@Override
	public void setup() {
		app.size(app.displayWidth, app.displayHeight);
	}
	
	
	
}
