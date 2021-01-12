package fr.julman.platform;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import fr.julman.platform.Locales.LangEntry;
import fr.julman.platform.Locales.Language;
import fr.julman.platform.player.Audio;
import fr.julman.platform.utils.GameState;

@SuppressWarnings({ "serial" })
public class Board extends JPanel {
	public Board() {
		Audio.playSound("chords.wav", 0);
		JOptionPane.showMessageDialog(this,
				"[beta/WARN] This game is currently in beta !\n[beta/WARN] The game can be instable !");

		try {
			startBoard();
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}

	private GameState GAME_STATE = GameState.IDLE;

	private Point play = new Point(10, 10);
	private Point settings = new Point(10, 50);
	private Point quit = new Point(10, 140);

	private Point lang_usa = new Point(10, 70);
	private Point lang_fra = new Point(10, 90);

	private final MouseListener button_listener = new MouseListener() {
		@SuppressWarnings("unchecked")
		@Override
		public void mouseReleased(MouseEvent e) {
			if (GAME_STATE == GameState.PLAYING) {
				return;
			}

			if (GAME_STATE == GameState.SETTINGS) {
				// Back button click check
				if (new Rectangle(play.x, play.y, 100, 20).contains(e.getPoint())) {
					GAME_STATE = GameState.IDLE;
					repaint();
				}

				if (new Rectangle(lang_usa.x, lang_usa.y, 140, 20).contains(e.getPoint())) {
					final JSONObject config = new JSONObject();
					config.put("language", "USA");

					FileWriter writer;
					try {
						writer = new FileWriter(Launcher.CONFIGURATION_FILE);
						writer.write(config.toJSONString());
						writer.flush();
						writer.close();
					} catch (IOException e1) {
						Audio.playSound("chords.wav", 0);
						JOptionPane.showMessageDialog(Board.this, "USA : Sorry, but changes will not saved.\n\nFRA : Désolé, mais les changements ne serront pas enregistrés.");
					}

					Launcher.LANG = Language.USA;
					repaint();
				}

				if (new Rectangle(lang_fra.x, lang_fra.y, 140, 20).contains(e.getPoint())) {
					final JSONObject config = new JSONObject();
					config.put("language", "FRA");

					FileWriter writer;
					try {
						writer = new FileWriter(Launcher.CONFIGURATION_FILE);
						writer.write(config.toJSONString());
						writer.flush();
						writer.close();
					} catch (IOException e1) {
						Audio.playSound("chords.wav", 0);
						JOptionPane.showMessageDialog(Board.this, "USA : Sorry, but changes will not saved.\n\nFRA : Désolé, mais les changements ne serront pas enregistrés.");
					}

					Launcher.LANG = Language.FRA;
					repaint();
				}
				
				if (new Rectangle(10, 150, 150, 20).contains(e.getPoint())) {
					repaint();
				}

				return;
			}

			if (GAME_STATE == GameState.IDLE) {
				// Play button click check
				if (new Rectangle(play.x, play.y, 100, 20).contains(e.getPoint())) {
					GAME_STATE = GameState.PLAYING;
					repaint();
				}

				// Settings button click check
				if (new Rectangle(settings.x, settings.y, 100, 20).contains(e.getPoint())) {
					GAME_STATE = GameState.SETTINGS;
					repaint();
				}

				// Quit button click check
				if (new Rectangle(quit.x, quit.y, 100, 20).contains(e.getPoint())) {
					Audio.playSound("chords.wav", 0);
					if (JOptionPane.showConfirmDialog(Board.this, Locales.getLangEntry(LangEntry.QUIT_CONFIRM),
							Locales.getLangEntry(LangEntry.QUIT_CONFIRM), 0x000) == JOptionPane.YES_OPTION) {
						Launcher.LOGGER.info("main", "Application exited with code 0");
						System.exit(0);
					}
				}

				return;
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}
	};

	@SuppressWarnings("unchecked")
	private void startBoard() throws ParseException, IOException {
		this.setBackground(getBackground());
		this.setForeground(getForeground());

		try {
			if (Launcher.CONFIGURATION_FILE.exists()) {
				FileReader reader = new FileReader(Launcher.CONFIGURATION_FILE);
				final JSONObject config = (JSONObject) new JSONParser().parse(reader);

				if (config.get("language") != null) {
					Language lang = Language.parse((String) config.get("language"));

					Launcher.LANG = lang;
				} else
					Launcher.LANG = Language.USA;
			} else {
				final JSONObject config = new JSONObject();
				config.put("language", "USA");

				FileWriter writer = new FileWriter(Launcher.CONFIGURATION_FILE);
				writer.write(config.toJSONString());
				writer.flush();
				writer.close();

				Launcher.LANG = Language.USA;
			}
		} catch (IOException e) {
			e.printStackTrace();
			Audio.playSound("chords.wav", 0);
			JOptionPane.showInternalMessageDialog(this, "USA : Sorry, but if you change game settings, changes will not saved.\n\nFRA : Désolé, mais si vous changer les paramètres du jeu, les changements ne serront pas enregistrés.");
		}
		
		Audio.playSound("main_title.wav", -0x001);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (GAME_STATE == GameState.IDLE) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 2000, 2000);

			g.setColor(Color.CYAN);
			g.fillRect(play.x, play.y, 100, 20);
			if (Locales.getLangEntry(LangEntry.SETTINGS_BUTTON) == Locales.FRA_SETTINGS_BUTTON) {
				g.fillRect(settings.x, settings.y, 120, 20);
			}
			g.fillRect(settings.x, settings.y, 100, 20);
			g.fillRect(quit.x, quit.y, 100, 20);

			g.setFont(new Font("Lucida Console", 20, 20));
			g.setColor(Color.DARK_GRAY);
			g.drawString(Locales.getLangEntry(LangEntry.PLAY_BUTTON), play.x + 2, play.y + 17);
			g.drawString(Locales.getLangEntry(LangEntry.SETTINGS_BUTTON), settings.x + 2, settings.y + 17);
			g.setColor(Color.RED);
			g.drawString(Locales.getLangEntry(LangEntry.QUIT_BUTTON), quit.x + 2, quit.y + 17);

			this.removeMouseListener(button_listener);
			this.addMouseListener(button_listener);
		} else if (GAME_STATE == GameState.PLAYING) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 2000, 2000);

			g.setColor(Color.WHITE);
			g.setFont(new Font("Lucida Consola", 30, 30));
			g.fillRect(0, 380, 20, 20);

			this.removeMouseListener(button_listener);
			this.addMouseListener(button_listener);
		} else if (GAME_STATE == GameState.SETTINGS) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 2000, 2000);

			g.setColor(Color.CYAN);
			g.fillRect(play.x, play.y, 100, 20);
			if (Launcher.LANG == Language.USA) {
				g.setColor(Color.YELLOW);
				g.fillRect(lang_usa.x, lang_usa.y, 140, 20);
				g.setColor(Color.CYAN);
				g.fillRect(lang_fra.x, lang_fra.y, 140, 20);
			} else {
				g.fillRect(lang_usa.x, lang_usa.y, 140, 20);
				g.setColor(Color.YELLOW);
				g.fillRect(lang_fra.x, lang_fra.y, 140, 20);
			}

			g.setFont(new Font("Lucida Console", 20, 20));
			g.setColor(Color.DARK_GRAY);
			g.drawString(Locales.getLangEntry(LangEntry.BACK_TEXT), play.x + 2, play.y + 17);
			
			g.setColor(Color.WHITE);
			g.drawString("Version : " + Launcher.VERSION, 20, 450);
			g.drawString(Locales.getLangEntry(LangEntry.LANGUAGE_TEXT) + " :", 10, 60);
			g.drawString(Locales.getLangEntry(LangEntry.MUSIC_ACTIVATION_TEXT) + " :", 10, 140);
			
			g.setColor(Color.CYAN);
			g.fillRect(10 , 150, 140, 20);
			g.setColor(Color.DARK_GRAY);
			g.drawString("-", 14 , 167);
			g.setColor(Color.WHITE);
			
			g.setColor(Color.DARK_GRAY);
			if (Launcher.LANG == Language.USA) {
				g.drawString("• English", lang_usa.x + 2, lang_usa.y + 17);
				g.drawString("  Français", lang_fra.x + 2, lang_fra.y + 17);
			} else {
				g.drawString("  English", lang_usa.x + 2, lang_usa.y + 17);
				g.drawString("• Français", lang_fra.x + 2, lang_fra.y + 17);
			}

			this.removeMouseListener(button_listener);
			this.addMouseListener(button_listener);
		}
	}
}
