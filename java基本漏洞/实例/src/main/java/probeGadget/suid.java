package probeGadget;

import java.io.Serializable;

public class suid implements Serializable {
    private static final long serialVersionUID = 100L;
    private String prop = "MyName";

    public void getProp(String var1) {
        this.prop = var1;
    }

    public String setProp() {
        return this.prop;
    }

    public suid() {
        this.prop = "MyName";
    }

    public void execute() {
        System.out.println("execute():" + this.prop);
    }
}
