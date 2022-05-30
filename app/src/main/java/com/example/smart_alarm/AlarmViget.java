package com.example.smart_alarm;

public class AlarmViget extends MainActivity{
    /*public void CreateViget(){
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.linearlayout);
        TextView textView = new TextView(this);
        ViewGroup.LayoutParams textViewLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setText("GGG");
        mainLayout.addView(textView);
    }*/
    private String time;

    public AlarmViget(String time){
        this.time = time;
    }
}
