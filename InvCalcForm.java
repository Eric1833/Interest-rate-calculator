
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.collections.*;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;
import java.text.*;

public class InvCalcForm extends Application
{
    private String[] rates = {"3.5","4.0","4.5","5.0","5.5","6.0","6.5","7.0","7.5","8.0","8.5","9.0","9.5","10.0"};
    private ComboBox<String> cboRates = new ComboBox<>();
    private CheckBox chkOut = new CheckBox("Write Report");
    private TextField tfInvestmentAmount = new TextField();
    private TextField tfNumYears = new TextField();
    private TextField tfAnnualRate = new TextField();
    private TextField tfFutureValue = new TextField();
    private TextField tfMsg = new TextField(); //for error and processing msgs
    private Button btCalculate = new Button("Calculate");

    public void start(Stage primaryStage)
     
    {
        //force all of the text fields to be 20 columns
        tfMsg.setPrefColumnCount(20); 
        
        GridPane pane = new GridPane();
        cboRates.setPrefWidth(260);
        cboRates.setValue("3.5");
        ObservableList<String> items = FXCollections.observableArrayList(rates);
        cboRates.getItems().addAll(items);
        
        pane.setHgap(5);
        pane.setVgap(5);
        
        pane.add(new Label("Investment Amount:"), 0, 0);
        pane.add(tfInvestmentAmount, 1, 0);
        pane.add(new Label("Number of Years:"), 0, 1);
        pane.add(tfNumYears, 1, 1);
        pane.add(new Label("Annual Interest Rate:"), 0, 2);
        pane.add(cboRates, 1, 2);
        pane.add(new Label("Future value:"), 0, 3);
        pane.add(tfFutureValue, 1, 3);
        pane.add(new Label("Message:"), 0, 4);
        pane.add(tfMsg, 1, 4);
        pane.add(btCalculate, 1, 5);
        pane.add(chkOut,1,5);
    
        
        tfInvestmentAmount.setAlignment(Pos.BOTTOM_RIGHT);
        tfNumYears.setAlignment(Pos.BOTTOM_RIGHT);
        tfAnnualRate.setAlignment(Pos.BOTTOM_RIGHT);
        tfFutureValue.setAlignment(Pos.BOTTOM_RIGHT);
        tfMsg.setAlignment(Pos.BOTTOM_RIGHT);
        btCalculate.setAlignment(Pos.BOTTOM_RIGHT);
        tfFutureValue.setEditable(false);
        tfMsg.setEditable(false);
        GridPane.setHalignment(btCalculate, HPos.RIGHT);
        
        //EventHandler<ActionEvent> handler = e ->
        /*{
            if(chkOut.isSelected())
            {   
                try{
                    Investment iv = new Investment();
                    PrintWriter out = new PrintWriter( new File ("report.txt"));
                    tfInvestmentAmount.requestFocus();
                    double investmentAmount = Double.parseDouble(tfInvestmentAmount.getText());
                    iv.setInvestmentAmount(investmentAmount);
            
                    tfNumYears.requestFocus();
                    int numYears = Integer.parseInt(tfNumYears.getText());
                    iv.setNumYears(numYears);
            
                    tfAnnualRate.requestFocus();
                    double annualRate = Double.parseDouble(cboRates.getValue());
                    iv.setAnnualRate(annualRate);
        
                    tfFutureValue.setText(String.format("$%.2f",iv.calcFutureValue()));
                    tfInvestmentAmount.requestFocus();
                    out.printf("Amount :%.2f  Years: %d  Rate: %.2f  FutureValue: %.2f %n", iv.getInvestmentAmount(),
                        iv.getNumYears(),iv.getAnnualRate(),iv.calcFutureValue());
                    out.close();
                }catch(FileNotFoundException ex){}
                catch (NumberFormatException ex)
                {
                    tfMsg.setText("Invalid Input");
                    tfFutureValue.setText("");
                }
                catch (IllegalArgumentException ex)
                {
                    tfMsg.setText(ex.getMessage());
                    tfFutureValue.setText("");
                }
            }
        };*/
        //chkOut.setOnAction(handler);
        btCalculate.setOnAction(e -> calculateInvestment());
        
        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Future Investment Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateInvestment()
    {
        try
        {
            Investment iv = new Investment();
            tfMsg.setText("");
            tfInvestmentAmount.requestFocus();
            double investmentAmount = Double.parseDouble(tfInvestmentAmount.getText());
            iv.setInvestmentAmount(investmentAmount);
            
            tfNumYears.requestFocus();
            int numYears = Integer.parseInt(tfNumYears.getText());
            iv.setNumYears(numYears);
            
            tfAnnualRate.requestFocus();
            double annualRate = Double.parseDouble(cboRates.getValue());
            iv.setAnnualRate(annualRate);
            
            tfFutureValue.setText(String.format("$%.2f",iv.calcFutureValue()));
            tfInvestmentAmount.requestFocus();
            tfMsg.setText("Calculation Processed");
            if(chkOut.isSelected())
            {     
                  
                      Calendar calendar = Calendar.getInstance();
                  
                      SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy HH_mm_ss");
                      String time = formatter.format(calendar.getTime());
                      String FileName = time;
                  
                      File file = new File( "Chen_Lu_lab9_" + time + ".txt");
                      //PrintWriter out = new PrintWriter(file);
                        //file.createNewFile();
                      PrintWriter out = new PrintWriter(file);
                      out.println("Amount :" + iv.getInvestmentAmount() +  " Years: " + iv.getNumYears() +
                              " Rate: " + iv.getAnnualRate() + " FutureValue: "+ iv.calcFutureValue()+ "\n");
                      out.println(tfMsg.getText() + "\n" +"Chen Lu " + time);

                      out.close();
                  

            }
        }
        catch(IOException ex){}
        catch (NumberFormatException ex)
        {
            tfMsg.setText("Invalid Input");
            tfFutureValue.setText("");
        }
        catch (IllegalArgumentException ex)
        {
            tfMsg.setText(ex.getMessage());
            tfFutureValue.setText("");
        }
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
}
