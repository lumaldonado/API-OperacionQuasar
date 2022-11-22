package ar.com.mercadolibre.challenge.drivers.trilaterationService;

import ar.com.mercadolibre.challenge.adapters.IMessageDecoderService;
import ar.com.mercadolibre.challenge.drivers.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DecoderService implements IMessageDecoderService {

    @Override
    public List<String> getMessage(List<List<String>> messages, List<String> msjDecode )throws BadRequestException {
        List<String> msgByColumn = new ArrayList<>();
        int sizeOfMinArrayMessage = messages.stream().min(Comparator.comparing(List::size)).get().size();
        cutLists(messages, sizeOfMinArrayMessage);
        messages.forEach(msgFromSatellite -> {

            String elementToAdd = msgFromSatellite.stream().findFirst().get();
            msgByColumn.add(elementToAdd);
            msgFromSatellite.remove(elementToAdd);

        });

        msjDecode.add(selectDuplicatedOrUniqueString(msgByColumn));

        if (sizeOfMinArrayMessage > 1)
            msjDecode = getMessage(messages, msjDecode);

        if(msjDecode.stream().anyMatch(String::isEmpty))
            throw new BadRequestException("Error: message can't decode");

        return msjDecode;
    }


    private String selectDuplicatedOrUniqueString(List<String> msjs) {
        Map<String, Long> counter = msjs.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Optional<Map.Entry<String, Long>> select = counter.entrySet().stream().filter(entry -> !entry.getKey().isEmpty()).findFirst();
        return select.map(Map.Entry::getKey).orElse("");
    }

    private void cutLists(List<List<String>> messages, Integer sizeOfMinMessage) {

        messages.forEach(strings -> {
            while (strings.size() > sizeOfMinMessage) strings.remove(strings.stream().findFirst().get());
        });
    }

}
