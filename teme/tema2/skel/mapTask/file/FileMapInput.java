package mapTask.file;

import mapTask.generic.MapInput;

public class FileMapInput implements MapInput {
    private final String type;
    private final Object info;

    public FileMapInput(String type, Object info) {
        this.type = type;
        this.info = info;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Object getInfo() {
        return info;
    }
}
