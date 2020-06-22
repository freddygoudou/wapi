package entity;

import android.widget.TextView;

public class AudioAndTextview {

    private TextView textView;
    private int audio;

    public AudioAndTextview() {
    }

    public AudioAndTextview(TextView textView, int audio) {
        this.textView = textView;
        this.audio = audio;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public int getAudio() {
        return audio;
    }

    public void setAudio(int audio) {
        this.audio = audio;
    }
}
