package javis.wearsyncservice;

import java.util.ArrayList;

/**
 * Created by AshleyTheMagnificant on 4/27/16.
 */
public class DataHolder {
    private ArrayList<String> data = new ArrayList<>();

    public String getData() {
        String results = "";
        if (!this.data.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                results += data.get(i) + "__";
            }
            return results;
        }
        else {
            return null;
        }
    }
    public void setData(String data) {
        this.data.add(data);
    }

    private static final DataHolder holder = new DataHolder();

    public static DataHolder getInstance() {return holder;}

}
