/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coira.util;
public class Util {

    // Función para convertir valor MIDI a nota (soporta todo el rango MIDI de 0 a 127)
    public static String midiToNote(int midiValue) {
        if (midiValue < 0 || midiValue > 127) {
            throw new IllegalArgumentException("El valor MIDI debe estar entre 0 y 127");
        }

        // Las notas en una octava (C, C#, D, D#, E, F, F#, G, G#, A, A#, B)
        String[] notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

        // Si el valor es menor que 21, devolver una octava negativa (por ejemplo, A-1, B-1)
        if (midiValue < 21) {
            int octave = -2 - (int) Math.floor((21 - midiValue) / 12.0);  // Calcula la octava negativa
            int noteIndex = midiValue % 12; // Calcula la nota (0 = C, 1 = C#, etc.)
            return notes[noteIndex] + octave;
        }

        // Si el valor es mayor que 108, devuelve una octava superior (por ejemplo, C9, D9)
        if (midiValue > 108) {
            int octave = 8 + (int) Math.floor((midiValue - 108) / 12.0); // Calcula la octava superior
            int noteIndex = midiValue % 12; // Calcula la nota (0 = C, 1 = C#, etc.)
            return notes[noteIndex] + octave;
        }

        // Para el rango estándar de A0 (21) a C8 (108), calculamos normalmente
        int octave = (midiValue - 21) / 12;  // Calcula la octava (21 es el MIDI de A0)
        int noteIndex = midiValue % 12;      // Calcula el índice de la nota (0 = C, 1 = C#, etc.)

        return notes[noteIndex] + octave;
    }

    // Función para convertir una nota a valor MIDI (soporta todo el rango MIDI de 0 a 127)
    public static int noteToMidi(String note) {
        int nota;
        try {
            nota = Integer.parseInt(note);
            return nota;
        } catch (Exception e) {
        }

// Convierte la nota a mayúsculas para hacerla insensible a mayúsculas/minúsculas
        note = note.toUpperCase();

        // Verifica si el formato de la nota es correcto
        if (note.length() < 2 || note.length() > 3) {
            throw new IllegalArgumentException("Formato de nota incorrecto");
        }

        // Extrae la nota y la octava
        String noteName = note.substring(0, note.length() - 1);
        int octave = Integer.parseInt(note.substring(note.length() - 1));

        // Las notas en una octava (C, C#, D, D#, E, F, F#, G, G#, A, A#, B)
        String[] notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

        // Encuentra el índice de la nota
        int noteIndex = -1;
        for (int i = 0; i < notes.length; i++) {
            if (notes[i].equals(noteName)) {
                noteIndex = i;
                break;
            }
        }

        if (noteIndex == -1) {
            throw new IllegalArgumentException("Nota no válida");
        }

        // Calcula el valor MIDI para la nota
        return 21 + noteIndex + (octave * 12); // 21 es el MIDI de A0
    }

    public static void main(String[] args) {
        // Prueba de la conversión MIDI a nota
        int midiValue = 60;  // MIDI para C4
        String note = midiToNote(midiValue);
        System.out.println("MIDI " + midiValue + " corresponde a la nota: " + note);

        // Prueba de la conversión de nota a MIDI
        String noteName = "c4";  // Nota en minúsculas
        int midiNote = noteToMidi(noteName);
        System.out.println("La nota " + noteName + " corresponde a MIDI: " + midiNote);

        // Prueba de valor MIDI fuera del rango de A0
        int midiBelowA0 = 19;  // Un valor menor que 21
        String belowA0 = midiToNote(midiBelowA0);
        System.out.println("MIDI " + midiBelowA0 + " es: " + belowA0);

        // Prueba de valor MIDI más allá de C8
        int midiAboveC8 = 109;  // Un valor mayor que 108
        String aboveC8 = midiToNote(midiAboveC8);
        System.out.println("MIDI " + midiAboveC8 + " es: " + aboveC8);
    }
}
