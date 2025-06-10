package back.vybz.comment_read_service.common.util;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CursorPage<T> {

    private List<T> content;
    private Boolean hasNext;
    private Instant nextCreatedAt;
    private String nextId;

    private CursorPage(List<T> content, Boolean hasNext, Instant nextCreatedAt, String nextId) {
        this.content = content;
        this.hasNext = hasNext;
        this.nextCreatedAt = nextCreatedAt;
        this.nextId = nextId;
    }

    /**
     * 기본 생성 메서드: 마지막 요소 기준으로 커서 추출
     */
    public static <T> CursorPage<T> of(List<T> items,
                                       int size,
                                       Function<T, Instant> getCreatedAtFn,
                                       Function<T, String> getIdFn) {
        boolean hasNext = items.size() > size;
        List<T> finalList = hasNext ? items.subList(0, size) : items;

        Instant nextCreatedAt = hasNext ? getCreatedAtFn.apply(finalList.get(finalList.size() - 1)) : null;
        String nextId = hasNext ? getIdFn.apply(finalList.get(finalList.size() - 1)) : null;

        return new CursorPage<>(finalList, hasNext, nextCreatedAt, nextId);
    }

    /**
     * VO 변환용 map 메서드
     */
    public <U> CursorPage<U> map(Function<? super T, ? extends U> mapper) {
        List<U> mappedContent = this.content.stream()
                .map(mapper)
                .collect(Collectors.toList());

        return new CursorPage<>(mappedContent, this.hasNext, this.nextCreatedAt, this.nextId);
    }
}
