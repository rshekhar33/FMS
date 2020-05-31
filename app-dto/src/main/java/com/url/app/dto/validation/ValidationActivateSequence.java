package com.url.app.dto.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence(value = { Default.class, BasicActivateGroup.class })
public interface ValidationActivateSequence {
}