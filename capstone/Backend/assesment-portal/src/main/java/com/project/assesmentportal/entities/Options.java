package com.project.assesmentportal.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Options class containing all the options.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Options {
    /**
     * The optionI of the question.
     */
    @NotBlank(message = "Option I is required")
    private String optionI;
    /**
     * The optionII of the question.
     */
    @NotBlank(message = "Option II is required")
    private String optionII;
    /**
     * The optionIII of the question.
     */
    @NotBlank(message = "Option III is required")
    private String optionIII;
    /**
     * The optionIV of the question.
     */
    @NotBlank(message = "Option IV is required")
    private String optionIV;
}
