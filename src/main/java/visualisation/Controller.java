package visualisation;

import evolutionWorld.Animal;
import evolutionWorld.MoveDirection;
import evolutionWorld.Time;
import evolutionWorld.WorldMap;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class Controller {
    public TextArea myCoolTextField;

    public void loginButtonClicked() {
        System.out.println("User logged in!");
    }

    public void loginButtonClicked2(){
        try {
            WorldMap map = new WorldMap(10, 10, 5, 10, 20);
            Time worldTime = new Time(map, 5);

            ArrayList<MoveDirection> squirrelGenes = new ArrayList<MoveDirection>();
            squirrelGenes.add(MoveDirection.NORTH);
            squirrelGenes.add(MoveDirection.NORTH);
            squirrelGenes.add(MoveDirection.NORTHEAST);
            Animal squirrel = new Animal(map, squirrelGenes);
            map.place(squirrel);

            ArrayList<MoveDirection> foxGenes = new ArrayList<MoveDirection>();
            foxGenes.add(MoveDirection.SOUTH);
            foxGenes.add(MoveDirection.SOUTH);
            foxGenes.add(MoveDirection.SOUTHWEST);
            Animal fox = new Animal(map, foxGenes);
            map.place(fox);

            myCoolTextField.setText(map.toString());

        }
        catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
