package dev.jlkeesh.qualify;

import java.util.List;

public class GrpcPostService implements PostService {
    private List<String> codes;

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    @Override
    public String toString() {
        return "GrpcPostService{" +
                "codes=" + codes +
                '}';
    }
}
