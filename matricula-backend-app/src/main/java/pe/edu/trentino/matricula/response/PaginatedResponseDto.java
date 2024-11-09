package pe.edu.trentino.matricula.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class PaginatedResponseDto<T> {
    private List<T> data;
    private int page;
    private int perPage;
    private long total;
    private int lastPage;

    public PaginatedResponseDto(List<T> data, int page, int perPage, long total) {
        this.data = data;
        this.page = page;
        this.perPage = perPage;
        this.total = total;
        this.lastPage = (int) Math.ceil((double) total / perPage);
    }
}
