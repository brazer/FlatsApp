package by.onliner.flatsapp.utils.pagination;

import rx.Observable;

public interface PagingListener<T> {
    Observable<T> onNextPage(int offset);
}
