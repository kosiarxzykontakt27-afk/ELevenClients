package pl.elevenclients.module;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        // Na razie brak modulow - beda dodawane pozniej.
    }

    public List<Module> getAll() { return modules; }
}
