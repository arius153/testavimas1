package com.example.testavimas1.service;

import com.example.testavimas1.repository.VartotojasRepository;
import com.example.testavimas1.repository.VeiksmasRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VeiksmasServiceTest {

    @Mock
    VartotojasRepository vartotojasRepository;

    @InjectMocks
    VartotojasService vartotojasService;

    @Mock
    VeiksmasRepository veiksmasRepository;

    @InjectMocks
    VeiksmasService veiksmasService;
}
