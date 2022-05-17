package com.example.readingisgood.audit;

import com.example.readingisgood.model.CustomerModel;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Optional;

@Component
public class AuditAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Object principalObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principalObj instanceof String)) {
            CustomerModel principal = (CustomerModel) principalObj;
            return Optional.of(principal.getId());
        }
        return Optional.of(-1L);
    }
}