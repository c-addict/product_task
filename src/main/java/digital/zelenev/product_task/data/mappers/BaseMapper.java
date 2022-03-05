package digital.zelenev.product_task.data.mappers;

import java.util.LinkedList;
import java.util.List;

public abstract class BaseMapper<D, B> implements IMapper<D, B> {

    @Override
    public List<D> convertList(List<B> list) {
        List<D> result = new LinkedList<>();
        for (B element : list) {
            result.add(convert(element));
        }
        return result;
    }
}
