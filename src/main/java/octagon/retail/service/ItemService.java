package octagon.retail.service;

import octagon.retail.repository.IItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private IItemRepository itemRepository;

}
