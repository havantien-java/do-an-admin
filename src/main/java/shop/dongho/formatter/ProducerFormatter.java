package shop.dongho.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import shop.dongho.model.Producer;
import shop.dongho.service.ProducerService;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

public class ProducerFormatter implements Formatter<Optional<Producer>> {
    private ProducerService producerService;

    @Autowired
    public ProducerFormatter(ProducerService producerService) {
        this.producerService = producerService;

    }

    public ProducerFormatter() {

    }

    @Override
    public Optional<Producer> parse(String text, Locale locale) throws ParseException {
        return producerService.findById(Integer.parseInt(text));
    }

    @Override
    public String print(Optional<Producer> object, Locale locale) {
        return null;
    }
}
