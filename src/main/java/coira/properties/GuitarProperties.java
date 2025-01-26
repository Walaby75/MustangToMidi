/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.properties;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Administrador
 */
public class GuitarProperties {
    
   
    private int string_initial_value_lowE=40;
    private int string_initial_value_A=45;
    private int string_initial_value_D=50;
    private int string_initial_value_G=55;
    private int string_initial_value_B=59;
    private int string_initial_value_highE=64;
    
    private int string_go_to_sustain=24;
    
    private int string_cc_selection_value_lowE=21;
    private int string_cc_selection_value_A=42;
    private int string_cc_selection_value_D=63;
    private int string_cc_selection_value_G=84;
    private int string_cc_selection_value_B=105;
    private int string_cc_selection_value_highE=126;
    
    private int string_key_selection_value_lowE=28;
    private int string_key_selection_value_A=33;
    private int string_key_selection_value_D=26;
    private int string_key_selection_value_G=31;
    private int string_key_selection_value_B=31;
    private int string_key_selection_value_highE=31;
    
    private boolean string_key_selection=true;
    
    private boolean string_cc_selection=true;
    
    private int string_initial_channel_lowE=1;
    private int string_initial_channel_A=2;
    private int string_initial_channel_D=3;
    private int string_initial_channel_G=4;
    private int string_initial_channel_B=5;
    private int string_initial_channel_highE=6;

    private int string_strength_max=127;
    private int string_strength_min=0;
    private int string_strength_var=5;
    private boolean control_slide_wheel=false;
    private int control_slide_wheel_variation=5;
    private int control_slide_up=31;
    private int control_slide_down=30;
    private boolean control_slide_onNote=true;
    private boolean control_slide_toNote=false;
    private boolean control_slide_sustainPedal=true;
    private boolean control_legato_onNote=false;
    private int control_legato_value=30;
    
    
    //28 electri6ity
    // 30 scarbee
    //hammer on acou6tic 30
    //pull off acou6tic 32
    private int control_hammer_on_value=30;
    private int control_hammer_on_strength=127;
    private boolean control_hammer_on_keyswitch=true;
    private boolean control_hammer_on_release_after_play=true;
    private int control_pull_off_value=30;
    private int control_pull_off_strength=50;
    private boolean control_pull_off_keyswitch=true;
    private boolean control_pull_off_release_after_play=true;
    //24 electricyti
    //31 aco6tic
    //30 scarbee
    private int control_pull_off_stop=30;
    private int control_hammer_on_stop=30;
            
    private boolean control_pull_hammer_wheel=false;
    private int control_pull_hammer_wheel_variation=5;
    private int control_pull_hammer_up=31;
    private int control_pull_hammer_down=30;
    private boolean control_pull_hammer_onNote=true;

    
    private int string_bend_strength = 110;
    private boolean control_bend_wheel=false;
    private int control_bend_wheel_variation=5;
    private int control_bend_wheel_tones=2;
    private int control_bend_unibend=28;
    private int control_bend_up=29;
    private int control_bend_down=-10;
    private int control_tremolo_wheel_tones=1;
    
    private boolean control_string_muteOnRelease =true;
    
    private boolean control_mute_note=false;
    private int control_mute_note_value=38;
    
    
    private boolean port_signal_sending_all=false;
    private boolean port_signal_sending_rotative=true;
    private int port_signal_sending_quantity=3;


    private boolean string_cutAlways=true;
    
    public int getString_initial_value_lowE() {
        return string_initial_value_lowE;
    }

    public void setString_initial_value_lowE(int string_initial_value_lowE) {
        this.string_initial_value_lowE = string_initial_value_lowE;
    }

    public int getString_initial_value_A() {
        return string_initial_value_A;
    }

    public void setString_initial_value_A(int string_initial_value_A) {
        this.string_initial_value_A = string_initial_value_A;
    }

    public int getString_initial_value_D() {
        return string_initial_value_D;
    }

    public void setString_initial_value_D(int string_initial_value_D) {
        this.string_initial_value_D = string_initial_value_D;
    }

    public int getString_initial_value_G() {
        return string_initial_value_G;
    }

    public void setString_initial_value_G(int string_initial_value_G) {
        this.string_initial_value_G = string_initial_value_G;
    }

    public int getString_initial_value_B() {
        return string_initial_value_B;
    }

    public void setString_initial_value_B(int string_initial_value_B) {
        this.string_initial_value_B = string_initial_value_B;
    }

    public int getString_initial_value_highE() {
        return string_initial_value_highE;
    }

    public void setString_initial_value_highE(int string_initial_value_highE) {
        this.string_initial_value_highE = string_initial_value_highE;
    }

    public int getString_initial_channel_lowE() {
        return string_initial_channel_lowE;
    }

    public void setString_initial_channel_lowE(int string_initial_channel_lowE) {
        this.string_initial_channel_lowE = string_initial_channel_lowE;
    }

    public int getString_initial_channel_A() {
        return string_initial_channel_A;
    }

    public void setString_initial_channel_A(int string_initial_channel_A) {
        this.string_initial_channel_A = string_initial_channel_A;
    }

    public int getString_initial_channel_D() {
        return string_initial_channel_D;
    }

    public void setString_initial_channel_D(int string_initial_channel_D) {
        this.string_initial_channel_D = string_initial_channel_D;
    }

    public int getString_initial_channel_G() {
        return string_initial_channel_G;
    }

    public void setString_initial_channel_G(int string_initial_channel_G) {
        this.string_initial_channel_G = string_initial_channel_G;
    }

    public int getString_initial_channel_B() {
        return string_initial_channel_B;
    }

    public void setString_initial_channel_B(int string_initial_channel_B) {
        this.string_initial_channel_B = string_initial_channel_B;
    }

    public int getString_initial_channel_highE() {
        return string_initial_channel_highE;
    }

    public void setString_initial_channel_highE(int string_initial_channel_highE) {
        this.string_initial_channel_highE = string_initial_channel_highE;
    }

    public int getString_strength_max() {
        return string_strength_max;
    }

    public void setString_strength_max(int string_strength_max) {
        this.string_strength_max = string_strength_max;
    }

    public int getString_strength_min() {
        return string_strength_min;
    }

    public void setString_strength_min(int string_strength_min) {
        this.string_strength_min = string_strength_min;
    }

    public boolean isControl_slide_wheel() {
        return control_slide_wheel;
    }

    public void setControl_slide_wheel(boolean control_slide_wheel) {
        this.control_slide_wheel = control_slide_wheel;
    }

    public int getControl_slide_wheel_variation() {
        return control_slide_wheel_variation;
    }

    public void setControl_slide_wheel_variation(int control_slide_wheel_variation) {
        this.control_slide_wheel_variation = control_slide_wheel_variation;
    }

    public int getControl_slide_up() {
        return control_slide_up;
    }

    public void setControl_slide_up(int control_slide_up) {
        this.control_slide_up = control_slide_up;
    }

    public int getControl_slide_down() {
        return control_slide_down;
    }

    public void setControl_slide_down(int control_slide_down) {
        this.control_slide_down = control_slide_down;
    }

    public boolean isControl_bend_wheel() {
        return control_bend_wheel;
    }

    public void setControl_bend_wheel(boolean control_bend_wheel) {
        this.control_bend_wheel = control_bend_wheel;
    }

    public int getControl_bend_wheel_variation() {
        return control_bend_wheel_variation;
    }

    public void setControl_bend_wheel_variation(int control_bend_wheel_variation) {
        this.control_bend_wheel_variation = control_bend_wheel_variation;
    }

    public int getControl_bend_wheel_tones() {
        return control_bend_wheel_tones;
    }

    public void setControl_bend_wheel_tones(int control_bend_wheel_tones) {
        this.control_bend_wheel_tones = control_bend_wheel_tones;
    }

    public int getControl_bend_unibend() {
        return control_bend_unibend;
    }

    public void setControl_bend_unibend(int control_bend_unibend) {
        this.control_bend_unibend = control_bend_unibend;
    }

    public int getControl_bend_up() {
        return control_bend_up;
    }

    public void setControl_bend_up(int control_bend_up) {
        this.control_bend_up = control_bend_up;
    }

    public int getControl_bend_down() {
        return control_bend_down;
    }

    public void setControl_bend_down(int control_bend_down) {
        this.control_bend_down = control_bend_down;
    }

    public int getControl_tremolo_wheel_tones() {
        return control_tremolo_wheel_tones;
    }

    public void setControl_tremolo_wheel_tones(int control_tremolo_wheel_tones) {
        this.control_tremolo_wheel_tones = control_tremolo_wheel_tones;
    }
    
    

    public int getString_strength_var() {
        return string_strength_var;
    }

    public void setString_strength_var(int string_strength_var) {
        this.string_strength_var = string_strength_var;
    }

    public boolean isString_cutAlways() {
        return string_cutAlways;
    }

    public void setString_cutAlways(boolean string_cutAlways) {
        this.string_cutAlways = string_cutAlways;
    }

    public boolean isControl_slide_onNote() {
        return control_slide_onNote;
    }

    public void setControl_slide_onNote(boolean control_slide_onNote) {
        this.control_slide_onNote = control_slide_onNote;
    }

    public boolean isControl_slide_toNote() {
        return control_slide_toNote;
    }

    public void setControl_slide_toNote(boolean control_slide_toNote) {
        this.control_slide_toNote = control_slide_toNote;
    }

    public boolean isControl_legato_onNote() {
        return control_legato_onNote;
    }

    public void setControl_legato_onNote(boolean control_legato_onNote) {
        this.control_legato_onNote = control_legato_onNote;
    }

    public int getControl_legato_value() {
        return control_legato_value;
    }

    public void setControl_legato_value(int control_legato_value) {
        this.control_legato_value = control_legato_value;
    }

    
    
    public boolean isControl_mute_note() {
        return control_mute_note;
    }

    public void setControl_mute_note(boolean control_mute_note) {
        this.control_mute_note = control_mute_note;
    }

    public int getControl_mute_note_value() {
        return control_mute_note_value;
    }

    public void setControl_mute_note_value(int control_mute_note_value) {
        this.control_mute_note_value = control_mute_note_value;
    }

    public boolean isControl_string_muteOnRelease() {
        return control_string_muteOnRelease;
    }

    public void setControl_string_muteOnRelease(boolean control_string_muteOnRelease) {
        this.control_string_muteOnRelease = control_string_muteOnRelease;
    }

    public boolean isPort_signal_sending_all() {
        return port_signal_sending_all;
    }

    public void setPort_signal_sending_all(boolean port_signal_sending_all) {
        this.port_signal_sending_all = port_signal_sending_all;
    }

    public boolean isPort_signal_sending_rotative() {
        return port_signal_sending_rotative;
    }

    public void setPort_signal_sending_rotative(boolean port_signal_sending_rotative) {
        this.port_signal_sending_rotative = port_signal_sending_rotative;
    }

    public int getPort_signal_sending_quantity() {
        return port_signal_sending_quantity;
    }

    public void setPort_signal_sending_quantity(int port_signal_sending_quantity) {
        this.port_signal_sending_quantity = port_signal_sending_quantity;
    }

    public int getString_cc_selection_value_lowE() {
        return string_cc_selection_value_lowE;
    }

    public void setString_cc_selection_value_lowE(int string_cc_selection_value_lowE) {
        this.string_cc_selection_value_lowE = string_cc_selection_value_lowE;
    }

    public int getString_cc_selection_value_A() {
        return string_cc_selection_value_A;
    }

    public void setString_cc_selection_value_A(int string_cc_selection_value_A) {
        this.string_cc_selection_value_A = string_cc_selection_value_A;
    }

    public int getString_cc_selection_value_D() {
        return string_cc_selection_value_D;
    }

    public void setString_cc_selection_value_D(int string_cc_selection_value_D) {
        this.string_cc_selection_value_D = string_cc_selection_value_D;
    }

    public int getString_cc_selection_value_G() {
        return string_cc_selection_value_G;
    }

    public void setString_cc_selection_value_G(int string_cc_selection_value_G) {
        this.string_cc_selection_value_G = string_cc_selection_value_G;
    }

    public int getString_cc_selection_value_B() {
        return string_cc_selection_value_B;
    }

    public void setString_cc_selection_value_B(int string_cc_selection_value_B) {
        this.string_cc_selection_value_B = string_cc_selection_value_B;
    }

    public int getString_cc_selection_value_highE() {
        return string_cc_selection_value_highE;
    }

    public void setString_cc_selection_value_highE(int string_cc_selection_value_highE) {
        this.string_cc_selection_value_highE = string_cc_selection_value_highE;
    }

    public boolean isString_cc_selection() {
        return string_cc_selection;
    }

    public void setString_cc_selection(boolean string_cc_selection) {
        this.string_cc_selection = string_cc_selection;
    }

    public boolean isControl_slide_sustainPedal() {
        return control_slide_sustainPedal;
    }

    public void setControl_slide_sustainPedal(boolean control_slide_sustainPedal) {
        this.control_slide_sustainPedal = control_slide_sustainPedal;
    }

    public boolean isControl_pull_hammer_wheel() {
        return control_pull_hammer_wheel;
    }

    public void setControl_pull_hammer_wheel(boolean control_pull_hammer_wheel) {
        this.control_pull_hammer_wheel = control_pull_hammer_wheel;
    }

    public int getControl_pull_hammer_wheel_variation() {
        return control_pull_hammer_wheel_variation;
    }

    public void setControl_pull_hammer_wheel_variation(int control_pull_hammer_wheel_variation) {
        this.control_pull_hammer_wheel_variation = control_pull_hammer_wheel_variation;
    }

    public int getControl_pull_hammer_up() {
        return control_pull_hammer_up;
    }

    public void setControl_pull_hammer_up(int control_pull_hammer_up) {
        this.control_pull_hammer_up = control_pull_hammer_up;
    }

    public int getControl_pull_hammer_down() {
        return control_pull_hammer_down;
    }

    public void setControl_pull_hammer_down(int control_pull_hammer_down) {
        this.control_pull_hammer_down = control_pull_hammer_down;
    }

    public boolean isControl_pull_hammer_onNote() {
        return control_pull_hammer_onNote;
    }

    public void setControl_pull_hammer_onNote(boolean control_pull_hammer_onNote) {
        this.control_pull_hammer_onNote = control_pull_hammer_onNote;
    }

    public int getString_bend_strength() {
        return string_bend_strength;
    }

    public void setString_bend_strength(int string_bend_strength) {
        this.string_bend_strength = string_bend_strength;
    }

    public int getControl_hammer_on_value() {
        return control_hammer_on_value;
    }

    public void setControl_hammer_on_value(int control_hammer_on_value) {
        this.control_hammer_on_value = control_hammer_on_value;
    }

    public int getControl_hammer_on_strength() {
        return control_hammer_on_strength;
    }

    public void setControl_hammer_on_strength(int control_hammer_on_strength) {
        this.control_hammer_on_strength = control_hammer_on_strength;
    }

    public int getControl_pull_off_value() {
        return control_pull_off_value;
    }

    public void setControl_pull_off_value(int control_pull_off_value) {
        this.control_pull_off_value = control_pull_off_value;
    }

    public int getControl_pull_off_strength() {
        return control_pull_off_strength;
    }

    public void setControl_pull_off_strength(int control_pull_off_strength) {
        this.control_pull_off_strength = control_pull_off_strength;
    }

    public boolean isControl_hammer_on_keyswitch() {
        return control_hammer_on_keyswitch;
    }

    public void setControl_hammer_on_keyswitch(boolean control_hammer_on_keyswitch) {
        this.control_hammer_on_keyswitch = control_hammer_on_keyswitch;
    }

    public boolean isControl_hammer_on_release_after_play() {
        return control_hammer_on_release_after_play;
    }

    public void setControl_hammer_on_release_after_play(boolean control_hammer_on_release_after_play) {
        this.control_hammer_on_release_after_play = control_hammer_on_release_after_play;
    }

    public boolean isControl_pull_off_keyswitch() {
        return control_pull_off_keyswitch;
    }

    public void setControl_pull_off_keyswitch(boolean control_pull_off_keyswitch) {
        this.control_pull_off_keyswitch = control_pull_off_keyswitch;
    }

    public boolean isControl_pull_off_release_after_play() {
        return control_pull_off_release_after_play;
    }

    public void setControl_pull_off_release_after_play(boolean control_pull_off_release_after_play) {
        this.control_pull_off_release_after_play = control_pull_off_release_after_play;
    }

    public int getControl_pull_off_stop() {
        return control_pull_off_stop;
    }

    public void setControl_pull_off_stop(int control_pull_off_stop) {
        this.control_pull_off_stop = control_pull_off_stop;
    }

    public int getControl_hammer_on_stop() {
        return control_hammer_on_stop;
    }

    public void setControl_hammer_on_stop(int control_hammer_on_stop) {
        this.control_hammer_on_stop = control_hammer_on_stop;
    }

    public int getString_key_selection_value_lowE() {
        return string_key_selection_value_lowE;
    }

    public void setString_key_selection_value_lowE(int string_key_selection_value_lowE) {
        this.string_key_selection_value_lowE = string_key_selection_value_lowE;
    }

    public int getString_key_selection_value_A() {
        return string_key_selection_value_A;
    }

    public void setString_key_selection_value_A(int string_key_selection_value_A) {
        this.string_key_selection_value_A = string_key_selection_value_A;
    }

    public int getString_key_selection_value_D() {
        return string_key_selection_value_D;
    }

    public void setString_key_selection_value_D(int string_key_selection_value_D) {
        this.string_key_selection_value_D = string_key_selection_value_D;
    }

    public int getString_key_selection_value_G() {
        return string_key_selection_value_G;
    }

    public void setString_key_selection_value_G(int string_key_selection_value_G) {
        this.string_key_selection_value_G = string_key_selection_value_G;
    }

    public int getString_key_selection_value_B() {
        return string_key_selection_value_B;
    }

    public void setString_key_selection_value_B(int string_key_selection_value_B) {
        this.string_key_selection_value_B = string_key_selection_value_B;
    }

    public int getString_key_selection_value_highE() {
        return string_key_selection_value_highE;
    }

    public void setString_key_selection_value_highE(int string_key_selection_value_highE) {
        this.string_key_selection_value_highE = string_key_selection_value_highE;
    }

    public boolean isString_key_selection() {
        return string_key_selection;
    }

    public void setString_key_selection(boolean string_key_selection) {
        this.string_key_selection = string_key_selection;
    }

    public int getString_go_to_sustain() {
        return string_go_to_sustain;
    }

    public void setString_go_to_sustain(int string_go_to_sustain) {
        this.string_go_to_sustain = string_go_to_sustain;
    }

    
    
    
    
    public GuitarProperties(String archivo){
        try {
            MyProperties prop = new MyProperties();
            File f = new File("");
            prop.load(new FileInputStream("./conf/"+archivo));
            
            
            
            string_initial_value_lowE=Integer.parseInt(prop.getProperty("string_initial_value_lowE","40"));
            string_initial_value_A=Integer.parseInt(prop.getProperty("string_initial_value_A","45"));
            string_initial_value_D=Integer.parseInt(prop.getProperty("string_initial_value_D","50"));
            string_initial_value_G=Integer.parseInt(prop.getProperty("string_initial_value_G","55"));
            string_initial_value_B=Integer.parseInt(prop.getProperty("string_initial_value_B","59"));
            string_initial_value_highE=Integer.parseInt(prop.getProperty("string_initial_value_highE","64"));
            string_cc_selection_value_lowE=Integer.parseInt(prop.getProperty("string_cc_selection_value_lowE","21"));
            string_cc_selection_value_A=Integer.parseInt(prop.getProperty("string_cc_selection_value_A","42"));
            string_cc_selection_value_D=Integer.parseInt(prop.getProperty("string_cc_selection_value_D","63"));
            string_cc_selection_value_G=Integer.parseInt(prop.getProperty("string_cc_selection_value_G","84"));
            string_cc_selection_value_B=Integer.parseInt(prop.getProperty("string_cc_selection_value_B","105"));
            string_cc_selection_value_highE=Integer.parseInt(prop.getProperty("string_cc_selection_value_highE","126"));
            string_go_to_sustain=Integer.parseInt(prop.getProperty("string_go_to_sustain","24"));
            string_key_selection_value_lowE=Integer.parseInt(prop.getProperty("string_key_selection_value_lowE","28"));
            string_key_selection_value_A=Integer.parseInt(prop.getProperty("string_key_selection_value_A","33"));
            string_key_selection_value_D=Integer.parseInt(prop.getProperty("string_key_selection_value_D","26"));
            string_key_selection_value_G=Integer.parseInt(prop.getProperty("string_key_selection_value_G","31"));
            string_key_selection_value_B=Integer.parseInt(prop.getProperty("string_key_selection_value_B","31"));
            string_key_selection_value_highE=Integer.parseInt(prop.getProperty("string_key_selection_value_highE","31"));
            string_key_selection=prop.getProperty("string_key_selection","no").toLowerCase().contains("s");
            string_cc_selection=prop.getProperty("string_cc_selection","no").toLowerCase().contains("s");
            string_initial_channel_lowE=Integer.parseInt(prop.getProperty("string_initial_channel_lowE","1"));
            string_initial_channel_A=Integer.parseInt(prop.getProperty("string_initial_channel_A","2"));
            string_initial_channel_D=Integer.parseInt(prop.getProperty("string_initial_channel_D","3"));
            string_initial_channel_G=Integer.parseInt(prop.getProperty("string_initial_channel_G","4"));
            string_initial_channel_B=Integer.parseInt(prop.getProperty("string_initial_channel_B","5"));
            string_initial_channel_highE=Integer.parseInt(prop.getProperty("string_initial_channel_highE","6"));
            string_strength_max=Integer.parseInt(prop.getProperty("string_strength_max","127"));
            string_strength_min=Integer.parseInt(prop.getProperty("string_strength_min","0"));
            string_strength_var=Integer.parseInt(prop.getProperty("string_strength_var","5"));
            control_slide_wheel=prop.getProperty("control_slide_wheel","no").toLowerCase().contains("s");
            control_slide_wheel_variation=Integer.parseInt(prop.getProperty("control_slide_wheel_variation","5"));
            control_slide_up=Integer.parseInt(prop.getProperty("control_slide_up","31"));
            control_slide_down=Integer.parseInt(prop.getProperty("control_slide_down","30"));
            control_slide_onNote=prop.getProperty("control_slide_onNote","no").toLowerCase().contains("s");
            control_slide_toNote=prop.getProperty("control_slide_toNote","no").toLowerCase().contains("s");
            control_legato_onNote=prop.getProperty("control_legato_onNote","no").toLowerCase().contains("s");
            control_legato_value=Integer.parseInt(prop.getProperty("control_legato_value","-10"));
            control_slide_sustainPedal=prop.getProperty("control_slide_sustainPedal","no").toLowerCase().contains("s");
            control_hammer_on_value=Integer.parseInt(prop.getProperty("control_hammer_on_value","30"));
            control_hammer_on_strength=Integer.parseInt(prop.getProperty("control_hammer_on_strength","127"));
            control_hammer_on_keyswitch=prop.getProperty("control_hammer_on_keyswitch","no").toLowerCase().contains("s");
            control_hammer_on_release_after_play=prop.getProperty("control_hammer_on_release_after_play","no").toLowerCase().contains("s");
            control_pull_off_value=Integer.parseInt(prop.getProperty("control_pull_off_value","30"));
            control_pull_off_strength=Integer.parseInt(prop.getProperty("control_pull_off_strength","50"));
            control_pull_off_keyswitch=prop.getProperty("control_pull_off_keyswitch","no").toLowerCase().contains("s");
            control_pull_off_release_after_play=prop.getProperty("control_pull_off_release_after_play","no").toLowerCase().contains("s");
            control_pull_off_stop=Integer.parseInt(prop.getProperty("control_pull_off_stop","30"));
            control_hammer_on_stop=Integer.parseInt(prop.getProperty("control_hammer_on_stop","30"));
            control_pull_hammer_wheel=prop.getProperty("control_pull_hammer_wheel","no").toLowerCase().contains("s");
            control_pull_hammer_wheel_variation=Integer.parseInt(prop.getProperty("control_pull_hammer_wheel_variation","5"));
            control_pull_hammer_up=Integer.parseInt(prop.getProperty("control_pull_hammer_up","31"));
            control_pull_hammer_down=Integer.parseInt(prop.getProperty("control_pull_hammer_down","30"));
            control_pull_hammer_onNote=prop.getProperty("control_pull_hammer_onNote","no").toLowerCase().contains("s");
            string_bend_strength=Integer.parseInt(prop.getProperty("string_bend_strength","110"));
            control_bend_wheel=prop.getProperty("control_bend_wheel","no").toLowerCase().contains("s");
            control_bend_wheel_variation=Integer.parseInt(prop.getProperty("control_bend_wheel_variation","5"));
            control_bend_wheel_tones=Integer.parseInt(prop.getProperty("control_bend_wheel_tones","2"));
            control_bend_unibend=Integer.parseInt(prop.getProperty("control_bend_unibend","28"));
            control_bend_up=Integer.parseInt(prop.getProperty("control_bend_up","29"));
            control_bend_down=Integer.parseInt(prop.getProperty("control_bend_down","-10"));
            control_tremolo_wheel_tones=Integer.parseInt(prop.getProperty("control_tremolo_wheel_tones","1"));
            control_string_muteOnRelease=prop.getProperty("control_string_muteOnRelease","no").toLowerCase().contains("s");
            control_mute_note=prop.getProperty("control_mute_note","no").toLowerCase().contains("s");
            control_mute_note_value=Integer.parseInt(prop.getProperty("control_mute_note_value","38"));
            port_signal_sending_all=prop.getProperty("port_signal_sending_all","no").toLowerCase().contains("s");
            port_signal_sending_rotative=prop.getProperty("port_signal_sending_rotative","no").toLowerCase().contains("s");
            port_signal_sending_quantity=Integer.parseInt(prop.getProperty("port_signal_sending_quantity","3"));
            string_cutAlways=prop.getProperty("string_cutAlways"," n").toLowerCase().contains("s");

            
            
            
        } catch (IOException ex) {
            Logger.getLogger(GuitarProperties.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salvarCFG(String archivo){
        try {
            MyProperties prop = new MyProperties();
            
            File f = new File("");
            //prop.load(new FileInputStream("./conf/"+archivo));
            prop.setProperty("string_initial_value_lowE",""+string_initial_value_lowE)	;
            prop.setProperty("string_initial_value_A",""+string_initial_value_A)	;
            prop.setProperty("string_initial_value_D",""+string_initial_value_D)	;
            prop.setProperty("string_initial_value_G",""+string_initial_value_G)	;
            prop.setProperty("string_initial_value_B",""+string_initial_value_B)	;
            prop.setProperty("string_initial_value_highE",""+string_initial_value_highE)	;
            prop.setProperty("string_cc_selection_value_lowE",""+string_cc_selection_value_lowE)	;
            prop.setProperty("string_cc_selection_value_A",""+string_cc_selection_value_A)	;
            prop.setProperty("string_cc_selection_value_D",""+string_cc_selection_value_D)	;
            prop.setProperty("string_cc_selection_value_G",""+string_cc_selection_value_G)	;
            prop.setProperty("string_cc_selection_value_B",""+string_cc_selection_value_B)	;
            prop.setProperty("string_cc_selection_value_highE",""+string_cc_selection_value_highE)	;
            prop.setProperty("string_key_selection_value_lowE",""+string_key_selection_value_lowE)	;
            prop.setProperty("string_key_selection_value_A",""+string_key_selection_value_A)	;
            prop.setProperty("string_key_selection_value_D",""+string_key_selection_value_D)	;
            prop.setProperty("string_key_selection_value_G",""+string_key_selection_value_G)	;
            prop.setProperty("string_key_selection_value_B",""+string_key_selection_value_B)	;
            prop.setProperty("string_key_selection_value_highE",""+string_key_selection_value_highE)	;
            prop.setProperty("string_key_selection",string_key_selection ? "s" : "n")	;
            prop.setProperty("string_cc_selection",string_cc_selection ? "s" : "n")	;
            prop.setProperty("string_initial_channel_lowE",""+string_initial_channel_lowE)	;
            prop.setProperty("string_initial_channel_A",""+string_initial_channel_A)	;
            prop.setProperty("string_initial_channel_D",""+string_initial_channel_D)	;
            prop.setProperty("string_initial_channel_G",""+string_initial_channel_G)	;
            prop.setProperty("string_initial_channel_B",""+string_initial_channel_B)	;
            prop.setProperty("string_initial_channel_highE",""+string_initial_channel_highE)	;
            prop.setProperty("string_strength_max",""+string_strength_max)	;
            prop.setProperty("string_strength_min",""+string_strength_min)	;
            prop.setProperty("string_strength_var",""+string_strength_var)	;
            prop.setProperty("control_slide_wheel",control_slide_wheel ? "s" : "n")	;
            prop.setProperty("control_slide_wheel_variation",""+control_slide_wheel_variation)	;
            prop.setProperty("control_slide_up",""+control_slide_up)	;
            prop.setProperty("control_slide_down",""+control_slide_down)	;
            prop.setProperty("control_slide_onNote",control_slide_onNote ? "s" : "n")	;
            prop.setProperty("control_slide_sustainPedal",control_slide_sustainPedal ? "s" : "n")	;
            prop.setProperty("control_hammer_on_value",""+control_hammer_on_value)	;
            prop.setProperty("control_hammer_on_strength",""+control_hammer_on_strength)	;
            prop.setProperty("control_hammer_on_keyswitch",control_hammer_on_keyswitch ? "s" : "n")	;
            prop.setProperty("control_hammer_on_release_after_play",control_hammer_on_release_after_play ? "s" : "n")	;
            prop.setProperty("control_pull_off_value",""+control_pull_off_value)	;
            prop.setProperty("control_pull_off_strength",""+control_pull_off_strength)	;
            prop.setProperty("control_pull_off_keyswitch",control_pull_off_keyswitch ? "s" : "n")	;
            prop.setProperty("control_pull_off_release_after_play",control_pull_off_release_after_play ? "s" : "n")	;
            prop.setProperty("control_pull_off_stop",""+control_pull_off_stop)	;
            prop.setProperty("control_hammer_on_stop",""+control_hammer_on_stop)	;
            prop.setProperty("control_pull_hammer_wheel",control_pull_hammer_wheel ? "s" : "n")	;
            prop.setProperty("control_pull_hammer_wheel_variation",""+control_pull_hammer_wheel_variation)	;
            prop.setProperty("control_pull_hammer_up",""+control_pull_hammer_up)	;
            prop.setProperty("control_pull_hammer_down",""+control_pull_hammer_down)	;
            prop.setProperty("control_pull_hammer_onNote",control_pull_hammer_onNote ? "s" : "n")	;
            prop.setProperty("string_bend_strength",""+string_bend_strength)	;
            prop.setProperty("control_bend_wheel",control_bend_wheel ? "s" : "n")	;
            prop.setProperty("control_bend_wheel_variation",""+control_bend_wheel_variation)	;
            prop.setProperty("control_bend_wheel_tones",""+control_bend_wheel_tones)	;
            prop.setProperty("control_bend_unibend",""+control_bend_unibend)	;
            prop.setProperty("control_bend_up",""+control_bend_up)	;
            prop.setProperty("control_bend_down",""+control_bend_down)	;
            prop.setProperty("control_tremolo_wheel_tones",""+control_tremolo_wheel_tones)	;
            prop.setProperty("control_string_muteOnRelease",control_string_muteOnRelease ? "s" : "n")	;
            prop.setProperty("control_mute_note",control_mute_note ? "s" : "n")	;
            prop.setProperty("control_mute_note_value",""+control_mute_note_value)	;
            prop.setProperty("port_signal_sending_all",port_signal_sending_all ? "s" : "n")	;
            prop.setProperty("port_signal_sending_rotative",port_signal_sending_rotative ? "s" : "n")	;
            prop.setProperty("port_signal_sending_quantity",""+port_signal_sending_quantity)	;
            prop.setProperty("string_cutAlways",string_cutAlways ? "s" : "n")	;

            prop.store(new FileOutputStream("./"+archivo), archivo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GuitarProperties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GuitarProperties.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    

}
