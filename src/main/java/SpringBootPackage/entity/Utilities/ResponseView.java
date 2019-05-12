package SpringBootPackage.entity.Utilities;

import java.util.HashMap;

public class ResponseView {

    private String status;
    private HashMap<String,? super IScoutItView> view;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HashMap<String, ? super IScoutItView> getView() {
        return view;
    }

    public void setView(HashMap<String, ? super IScoutItView> view) {
        this.view = view;
    }

    private enum statusEnum
    {
        SUCCESS("SUCCESS"),
        FAILURE("FAILURE"),
        UNKNOWN("UNKNOWN");
        private  String value;
        statusEnum(String value){
            this.value=value;
        }
        private String getValue(){
            return value;
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    }

}



