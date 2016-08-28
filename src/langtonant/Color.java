/*
MIT License

Copyright (c) 2016 Tondeur Herve

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package langtonant;

/**
 *
 * @author herve
 */
public class Color {
   //color colorized
   boolean colorized=false;
   String colorS="none";

    /**
     * constructor
     */
    public Color() {
        colorized=false;
        colorS="none"; 
    }
    
    /**
     *
     * @param colorize
     * @param colorName
     */
    public Color(boolean colorize, String colorName){
        colorized=colorize;
        colorS=colorName;
    }

    /**
     *
     * @return
     */
    public boolean isColorized() {
        return colorized;
    }

    /**
     *
     * @param state
     */
    public void setColorized(boolean state) {
        this.colorized = state;
    }

    /**
     *
     * @return
     */
    public String getColorS() {
        return colorS;
    }

    /**
     *
     * @param colorS
     */
    public void setColorS(String colorS) {
        this.colorS = colorS;
    }
   
   
    
}
