import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import uk.ac.leedsbeckett.oop.OOPGraphics;


public class GraphicSystem extends OOPGraphics{
    public String filename;
    String EmptyStringOne = "";
    public GraphicSystem() {
        setPanelSize(800,400);
        JFrame MainFrame = new JFrame();
        MainFrame.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
        MainFrame.setLayout(new FlowLayout());
        JButton helpButton = new JButton("Help");

        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "THE FOLLOWING ARE COMMANDS THAT CAN BE ENTERED ON TEXTFIELD:"+"\n" +
                        "'about': draws a graphic design stating OOP."+"\n" +
                        "'clear': clears the display."+"\n" +
                        "'reset': resets the turtle back to its inital position."+"\n"+
                        "'penup': lifts the pen up so the turtle doesnt draw"+"\n" +
                        "'pendown': places the pen down so that the turtle now draws on display for any drawing command given"+"\n" +
                        "'black': changes the pen colour to black"+"\n" +
                        "'green': changes the pen colour to green"+"\n"+
                        "'blue': changes the pen colour to blue"+"\n" +
                        "'red': changes the pen colour to red"+"\n" +
                        "'white': changes the pen colour to white"+"\n" +
                        "'rainbow': produces a rectangle with all colours in a rainbow"+"\n" +
                        "'mandala': draws a simple mandala art (only from its initial position)"+"\n" +
                        "'turnleft <parameter>': turns left for any angle given as parameter as integer"+"\n" +
                        "'turnright <parameter>':turns right for any angle given as parameter as integer"+"\n"+
                        "'forward <parameter>': moves forward for distance given as parameter as integer"+"\n" +
                        "'backward <parameter>': moves backward for distance given as parameter as integer"+"\n" +
                        "'circle <parameter>': draws a circle for radius given as parameter as integer"+"\n" +
                        "'triangle <parameter>': draws an equilateral triangle for length of side given as parameter as integer"+"\n" +
                        "'square <parameter>': draws a square for length of side given as parameter as integer"+"\n" +
                        "'penwidth <parameter>': sets the width of pen for integer between 1 and 9 given as parameter"+"\n" +
                        "'pencolour <parameter>,<parameter>,<parameter>': sets an rgb colour for pen for integers given as parameters between 0 and 255"+"\n" +
                        "'triangle <parameter>,<parameter>,<parameter>': draws a scalene triangle with three sides given as parameter as integer"+"\n" +
                        "'righttriangle <parameter>,<parameter>,<parameter>': draws a right triangle with three sides given as parameter in 3:4:5 ratio as integer"+"\n" +
                        "'bgc <parameter>,<parameter>,<parameter>': sets the colour of the display for integers given as parameter between 0 and 255 "+"\n" +
                        "'save <parameter>': saves all the commands typed by the user into a text file given as parameter(as String), and also saves a jpeg image of the panel with file name given as parameter"+"\n" +
                        "'load <parameter>': loads the text file and jpeg image saved by the user for filename as string given as parameter");
            }
        });
        MainFrame.add(this);
        MainFrame.add(helpButton);
        MainFrame.pack();
        MainFrame.setVisible(true);
        //about();
        clear();
    }
    @Override
    public void processCommand(String command) {
        String Rem = command.strip();
        String seperator = " ";
        String seperator2 = ",";
        String[] Str1 = command.split(seperator);
        String[] list = {"turnleft", "turnright", "forward", "backward", "square", "save", "load", "penwidth", "triangle", "pencolour", "righttriangle", "mandala", "bgc"};
        String StringArray = Arrays.toString(list);
        try {
            if (Rem.equals("about")) {
                about();
                save("about");
            } else if (Rem.equals("clear")) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int result = JOptionPane.showConfirmDialog(null, "Would You Like to Save your canvas", "Warning", dialogButton);
                if (result == JOptionPane.YES_OPTION) {
                    String pass = JOptionPane.showInputDialog("Enter Filename");
                    if (pass == null) {
                        clearInterface();
                        clear();
                    } else {
                        filename(pass);
                        saveImg();
                        toWrite();
                        clearInterface();
                        clear();
                    }
                } else if (result == JOptionPane.NO_OPTION) {
                    save("clear");
                    clearInterface();
                    clear();
                }
            } else if (command.isBlank()) {
                displayMessage("Enter a Command");
            } else if (Rem.equals("reset")) {
                save("reset");
                resetit();
            } else if (Rem.equals("penup")) {
                save("penup");
                penUp();
            } else if (Rem.equals("pendown")) {
                save("pendown");
                penDown();
            } else if (Rem.equals("black")) {
                save("black");
                black();
            } else if (Rem.equals("mandala")) {
                int x = getxPos();
                int y = getyPos();
                if (x != 400 && y != 201) {
                    displayMessage("Reset your turtle");
                } else {
                    if (getDirection() != 90) {
                        displayMessage("Reset your turtle");
                    } else {
                        save("mandala");
                        mandala();
                    }
                }
            } else if (Rem.equals("blue")) {
                save("blue");
                blue();
            } else if (Rem.equals("green")) {
                save("green");
                green();
            } else if (Rem.equals("rainbow")) {
                save("rainbow");
                rainbow();
            } else if (Rem.equals("white")) {
                save("white");
                white();
            } else if (Rem.equals("red")) {
                save("red");
                red();
            } else if (command.equals("turnleft " + Str1[1])) {
                int Numb;
                Numb = Integer.parseInt(Str1[1]);
                turnleft(Numb);
                save("turnleft " + Str1[1]);
            } else if (command.equals("turnright " + Str1[1])) {
                int Number1;
                Number1 = Integer.parseInt(Str1[1]);
                turnright(Number1);
                save("turnright " + Str1[1]);
            } else if (command.equals("forward " + Str1[1])) {
                int x = getxPos();
                int y = getyPos();
                int Number2;
                Number2 = Integer.parseInt(Str1[1]);
                if (Number2 < 1 || Number2 >= 180) {
                    displayMessage("Enter number between 1 and 180");
                } else {
                    if(x + Number2 <= 800 && x + Number2 >= 0 && y + Number2 <= 400 && y + Number2 >= 0){
                        forward(Number2);
                        save("forward " + Str1[1]);
                    } else {
                        displayMessage("Turtle would go of display");
                    }
                }
            } else if (command.equals("backward " + Str1[1])) {
                int Number3;
                int x1 = getxPos();
                int y1 = getyPos();
                Number3 = Integer.parseInt(Str1[1]);
                if (Number3 < 1 || Number3 >= 180) {
                    displayMessage("Enter number between 1 and 180");
                } else {
                    if(x1 - Number3 >= 0 && x1 - Number3 <= 800 && y1 - Number3 >= 0 && y1 - Number3 <= 400){
                        forward(-Number3);
                        save("backward " + Str1[1]);
                    } else {
                        displayMessage("Turtle would go of display");
                    }
                }
            }
            else if (command.equals("save " + Str1[1])) {
                filename(Str1[1]);
                saveImg();
                toWrite();
                displayMessage("Saved");

            } else if (command.equals("load " + Str1[1])) {
                //load(Str1[1]);
                loadImg(Str1[1]);
            } else if (command.equals("circle " + Str1[1])) {
                int Numbr;
                Numbr = Integer.parseInt(Str1[1]);
                if (Numbr < 1 || Numbr > 200) {
                    displayMessage("Enter length between 1 and 180");
                } else {
                    getPenColour();
                    drawCircle(Numbr, getxPos(), getyPos());
                    save("circle " + Str1[1]);
                }
            } else if (command.equals("square " + Str1[1])) {
                int Number41;
                Number41 = Integer.parseInt(Str1[1]);
                square(Number41);
            } else if (command.equals("triangle " + Str1[1])) {
                int Number51;
                Number51 = Integer.parseInt(Str1[1]);
                if (Number51 < 1 || Number51 > 200) {
                    displayMessage("Enter length between 1 and 199");
                } else {
                    equalTriangle(Number51);
                    save("triangle " + Str1[1]);
                }
            } else if (command.equals("penwidth " + Str1[1])) {
                int Number63;
                Number63 = Integer.parseInt(Str1[1]);
                if ((Number63 < 10) && (Number63 > 0)) {
                    penWidth(Number63);
                    save("penwidth " + Str1[1]);
                } else {
                    displayMessage("Enter number between 1 and 9");
                }
            } else if (!Objects.equals(Str1[0], StringArray)) {
                displayMessage("Enter Valid Command");
            }
        } catch (NumberFormatException e) {
            clearInterface();
            displayMessage("Enter Integer as parameter");
        } catch (ArrayIndexOutOfBoundsException f) {
            if (StringArray.contains(command.strip())) {
                displayMessage("Enter parameter");
            } else {
                displayMessage("Enter Valid Command");
            }
        }
        try {
            String[] strArr = Str1[1].split(seperator2);
            if (command.equals("pencolour " + strArr[0] + "," + strArr[1] + "," + strArr[2])) {
                int number61, number62, number63;
                number61 = Integer.parseInt(strArr[0]);
                number62 = Integer.parseInt(strArr[1]);
                number63 = Integer.parseInt(strArr[2]);
                rgbColour(number61, number62, number63);
                save("pencolour " + Str1[1]);
            } else if (command.equals("triangle " + strArr[0] + "," + strArr[1] + "," + strArr[2])) {
                int Num1 = Integer.parseInt(strArr[0]);
                int Num2 = Integer.parseInt(strArr[1]);
                int Num3 = Integer.parseInt(strArr[2]);
                clearInterface();
                scaleneTriangle(Num1, Num2, Num3);
            } else if (command.equals("bgc " + strArr[0] + "," + strArr[1] + "," + strArr[2])) {
                int Col1 = Integer.parseInt(strArr[0]);
                int Col2 = Integer.parseInt(strArr[1]);
                int Col3 = Integer.parseInt(strArr[2]);
                Color obj = new Color(Col1, Col2, Col3);
                setBackground_Col(obj);
                clear();
                save("bgc " + Str1[1]);


            } else if (command.equals("righttriangle " + strArr[0] + "," + strArr[1] + "," + strArr[2])) {
                clearInterface();
                int Num1 = Integer.parseInt(strArr[0]);
                int Num2 = Integer.parseInt(strArr[1]);
                int Num3 = Integer.parseInt(strArr[2]);
                rightTriangle(Num1, Num2, Num3);
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        } catch (NumberFormatException f) {
            displayMessage("Enter integer as parameter");
        } catch (IllegalArgumentException g) {
            displayMessage("Invalid Parameter");
        }
    }
    public void scaleneTriangle(int side1,int side2, int side3){
        if (side1+side2 > side3 && side2+side3 > side1 && side1+side3 > side2){
            double angleA = Math.acos(((side2 * side2) + (side3 * side3) - (side1 * side1)) / (2.0 * side2 * side3));
            double angleB = Math.acos(((side1 * side1) + (side3 * side3) - (side2 * side2)) / (2.0 * side1 * side3));
            double angleC = Math.acos(((side1 * side1) + (side2 * side2) - (side3 * side3)) / (2.0 * side1 * side2));

            int angleADegrees = (int) Math.toDegrees(angleA);
            int angleBDegrees = (int) Math.toDegrees(angleB);
            int angleCDegrees = (int) Math.toDegrees(angleC);
            int[] angle = {angleADegrees,angleBDegrees,angleCDegrees};
            if (side1 > 200 && side2 > 200 && side3 > 200){displayMessage("Enter length between 1 and 200");}
            else{
                int[] asc = {side1,side2,side3};

                Arrays.sort(angle);
                Arrays.sort(asc);

                turnRight(90);
                penDown();
                forward(asc[2]);
                turnRight(180-angle[1]);
                forward(asc[0]);
                turnRight(180-angle[2]);
                forward(asc[1]);
                turnRight(angle[0]);
                save("triangle " + side1+","+side2+","+side3);
            }
        }
        else{displayMessage("The sides do not form a triangle");}
    }
    public void mandala(){
        int size = 100;
        int x = getxPos();
        int y = getyPos();
        penDown();
        drawCircle(size,x,y);
        turnleft(190);
        equalTriangle((int)(1.72*size));
        turnRight(150);penUp();forward(size);penDown();drawCircle((size/2),getxPos(),getyPos());
        penUp();forward(-(int)(size/1.38));turnleft(90);square((int)(size/1.5));turnRight(45);
        penUp();forward((int)(size*0.46));penDown();drawCircle((int)(size*0.30),getxPos(),getyPos());turnleft(265);forward((int)(size*2*0.30));
        turnRight(180);equalTriangle((int)(size*2*0.25));




    }
    public void rightTriangle(int num1,int num2, int num3){
        int[] sides = {num1,num2,num3};
        Arrays.sort(sides);
        if(sides[2]*sides[2] == sides[0]*sides[0] + sides[1]*sides[1]){
            double angleA = Math.acos(((sides[0] * sides[0]) + (sides[2] * sides[2]) - (sides[1] * sides[1])) / (2.0 * sides[0] * sides[2]));
            double angleB = Math.acos(((sides[2] * sides[2]) + (sides[1] * sides[1]) - (sides[0] * sides[0])) / (2.0 * sides[2] * sides[1]));
            int angleADegrees = (int) Math.toDegrees(angleA);
            int angleBDegrees = (int) Math.toDegrees(angleB);
            penDown();
            turnRight(90);
            forward(sides[1]);
            turnRight(90);
            forward(sides[0]);
            turnRight(180 -angleADegrees);
            forward(sides[2]);
            turnRight(180-angleBDegrees);
            save("righttriangle " + num1+","+num2+","+num3);

        }
        else{displayMessage("Invalid sides");}

    }
    public void rgbColour(int col1, int col2, int col3){
        Color Obj1 = new Color(col1,col2,col3);
        setPenColour(Obj1);
    }
    public void equalTriangle(int num24) {
        penDown();
        turnLeft(30);
        forward(num24);
        turnRight(120);
        forward(num24);
        turnRight(120);
        forward(num24);

    }
    public String filename(String Filename){
        this.filename = Filename;
        return Filename;
    }
    public void toWrite() {
        try {
            FileWriter MyWriter = new FileWriter(filename+".txt",true);
            MyWriter.write(EmptyStringOne);
            MyWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void penWidth(int num43) {

        setStroke(num43);
    }
    public void saveImg(){
        try{
            File f = new File("/Users/sreehari/Pictures/"+filename+".jpeg");
            ImageIO.write(getBufferedImage(),"jpeg",f);
        }catch(Exception e ){
            displayMessage("Error");
        }
    }
    public void loadImg(String img){
        try{
            clear();
            BufferedImage imj = ImageIO.read(new File("/Users/sreehari/Pictures/"+img+".jpeg"));
            setBufferedImage(imj);
        }catch(Exception e){
            displayMessage("Enter existing filename");
        }
    }
    public void save(String ToSave) {
        EmptyStringOne = EmptyStringOne + ToSave+"\n";
        clearInterface();}
    public void turnleft(int num23) {
        clearInterface();
        turnLeft(num23);
    }

    public void turnright(int num1) {
        clearInterface();
        turnRight(num1);
    }

    public void resetit() {
        reset();
        setPenColour(Color.red);
    }
    public void rainbow(){
        penWidth(8);
        turnLeft(90);
        penDown();
        Color Obj1 = new Color(255,  192, 203);
        setPenColour(Obj1);
        forward(200);
        penUp();
        turnleft(90);
        forward(15);
        turnleft(90);
        Color Obj2 = new Color(160,  32,  240);
        setPenColour(Obj2);
        penDown();
        forward(200);
        penUp();
        turnRight(90);
        forward(15);
        turnRight(90);
        Color Obj3 = new Color(0,  0,  255);
        setPenColour(Obj3);
        penDown();
        forward(200);
        penUp();
        turnleft(90);
        forward(15);
        turnleft(90);
        Color Obj4 = new Color(0, 255, 255);
        setPenColour(Obj4);
        penDown();
        forward(200);
        penUp();
        turnRight(90);
        forward(15);
        turnRight(90);
        Color Obj5 = new Color(0, 255, 0);
        setPenColour(Obj5);
        penDown();
        forward(200);
        penUp();
        turnleft(90);
        forward(15);
        turnleft(90);
        Color Obj6 = new Color(255, 255, 0);
        setPenColour(Obj6);
        penDown();
        forward(200);
        penUp();
        turnRight(90);
        forward(15);
        turnRight(90);
        Color Obj7 = new Color(255, 128, 0);
        setPenColour(Obj7);
        penDown();
        forward(200);
        penUp();
        turnleft(90);
        forward(15);
        turnleft(90);
        setPenColour(Color.RED);
        penDown();
        forward(200);
        reset();
    }
    public void square(int num21) {
        if(num21 > 200 || num21 < 0){displayMessage("Enter number between 1 and 199");}
        else {
            penDown();
            for (int i = 0; i <= 3; i++) {
                forward(num21);
                turnRight(90);
            }
            save("square " + num21);
        }
    }
    public void load(String toLoad){
        try {
            File Obj44 = new File(toLoad + ".txt");
            Scanner readLines = new Scanner(Obj44);
            while (readLines.hasNextLine()) {
                String toExecute = readLines.nextLine();
                processCommand(toExecute);
            }
        } catch (RuntimeException e) {
            displayMessage("Enter Valid Filename");
        } catch (FileNotFoundException R) {
            displayMessage("Enter valid Filename");
        }
    }
    public void black() {
        setPenColour(Color.black);
    }public void blue() {
        setPenColour(Color.BLUE);
    }
    public void green() {
        setPenColour(Color.green);
    }

    public void red() {
        setPenColour(Color.red);
    }

    public void white() {
        setPenColour(Color.white);
    }
}


abstract class  MainClass extends OOPGraphics {

    public static void main(String[] args) {
        new GraphicSystem();



    }
}