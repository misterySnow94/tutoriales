package Wilson.ProblemWilson.service.impl.serializador;

import Wilson.ProblemWilson.entity.Trabajos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class TrabajosModelListener extends AbstractMongoEventListener<Trabajos> {
    private DefaultSequenceGeneratorService sequenceGenerator;

    @Autowired
    public TrabajosModelListener(DefaultSequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Trabajos> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Trabajos.SEQUENCE_NAME));
        }
    }

}
