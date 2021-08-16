package Exam;

import java.util.Optional;

import Client.ClientUI;
import Client.MyClient;
import Login.MyClientBoundary;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * class to present teacher report second screen GUI
 * 
 * @author Group 6
 *
 */
public class TeacherReport2Boundary {
	@FXML
	private Button logOutBtn;

	@FXML
	private BarChart<String, Number> graph;

	@FXML
	private CategoryAxis grades;

	@FXML
	private NumberAxis numOfStudents;

	@FXML
	private Label lblIndetifier;

	@FXML
	private Label graphExamName;

	@FXML
	private Label AvgTxt;

	@FXML
	private Label MedianTxt;

	@FXML
	private Button back;
	@FXML
	private Label lblNumStarted;

	@FXML
	private Label lblNumSuccess;

	@FXML
	private Label lblNumFail;

	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {
		lblIndetifier.setText(MyClient.teacherFullName);

		graphExamName.setText(TeacherReportBoundary.cou);
		AvgTxt.setText(MyClient.reportInfo.get(0));
		MedianTxt.setText(MyClient.reportInfo.get(1));
		lblNumStarted.setText("   " + MyClient.reportInfo.get(12));
		lblNumSuccess.setText("   " + MyClient.reportInfo.get(13));
		lblNumFail.setText("   " + MyClient.reportInfo.get(14));

		Series<String, Number> set1 = new XYChart.Series<>();
		set1.getData().add(new XYChart.Data<String, Number>("0-9", Integer.parseInt(MyClient.reportInfo.get(2))));
		set1.getData().add(new XYChart.Data<String, Number>("10-19", Integer.parseInt(MyClient.reportInfo.get(3))));
		set1.getData().add(new XYChart.Data<String, Number>("20-29", Integer.parseInt(MyClient.reportInfo.get(4))));
		set1.getData().add(new XYChart.Data<String, Number>("30-39", Integer.parseInt(MyClient.reportInfo.get(5))));
		set1.getData().add(new XYChart.Data<String, Number>("40-49", Integer.parseInt(MyClient.reportInfo.get(6))));
		set1.getData().add(new XYChart.Data<String, Number>("50-59", Integer.parseInt(MyClient.reportInfo.get(7))));
		set1.getData().add(new XYChart.Data<String, Number>("60-69", Integer.parseInt(MyClient.reportInfo.get(8))));
		set1.getData().add(new XYChart.Data<String, Number>("70-79", Integer.parseInt(MyClient.reportInfo.get(9))));
		set1.getData().add(new XYChart.Data<String, Number>("80-89", Integer.parseInt(MyClient.reportInfo.get(10))));
		set1.getData().add(new XYChart.Data<String, Number>("90-100", Integer.parseInt(MyClient.reportInfo.get(11))));

		graph.getData().addAll(set1);
	}

	/**
	 * start GUI for teacher exams report
	 * 
	 * @param primaryStage Stage object
	 * @throws Exception in case starting the stage was unsuccessful
	 */
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/Exam/TeacherExamReport.fxml"));
		Scene scene = new Scene(root);
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/logo.png")));
		primaryStage.setTitle("Teacher report form");
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {

				// consume event
				event.consume();

				// show close dialog
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Close Confirmation");
				alert.setHeaderText("Do you really want to quit?");
				alert.initOwner(primaryStage);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					ClientUI.chat.client.quit();
					Platform.exit();
				}
			}
		});
	}

	/**
	 * return to teachers report boundary GUI
	 * 
	 * @param event user clicks back button
	 */
	@FXML
	void backbtn(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
		TeacherReportBoundary trb = new TeacherReportBoundary();
		Stage primaryStage = new Stage();
		try {
			trb.start(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * logout, return to login GUI
	 * 
	 * @param event user clicks logout button
	 */
	@FXML
	void logout(ActionEvent event) {
		ClientUI.chat.accept("logout_" + MyClientBoundary.usName + "_" + MyClientBoundary.pass);
		if (MyClient.statusINOUT.equals("logout")) {
			((Node) event.getSource()).getScene().getWindow().hide();
			MyClientBoundary login = new MyClientBoundary();
			try {
				Stage primaryStage = new Stage();
				login.start(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}