package Data;

import android.widget.TextView;

import com.example.icnature.R;

public class Plant extends Wildlife {
    public Plant(String name, String scientificName, String habitat, String description) {
        super(name, scientificName, habitat, description);
    }

    public String toString() {
        String line = "";

        line = line + "Plant Information:"+ "\n";
        line = line + "Name: " + getName()+ "\n";
        line = line + "Scientific Name: " + getScientificName()+ "\n";
        line = line + "Habitat: " + getHabitat()+ "\n";
        line = line + "Description: " + getDescription()+ "\n";

        return line;
    }

    public void updateTV (TextView mTextView){

       // mTextView.findViewById(R.id.plantTV);

        mTextView.setText (this.toString());
    }
}
