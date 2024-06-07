package lk.ijse.gdse.aad.spring_boot_coursework.IdGen;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String prefix = "gen-";
        String suffix = String.format("%03d", UUID.randomUUID().hashCode() & Integer.MAX_VALUE % 1000);
        return prefix + suffix;
    }
}