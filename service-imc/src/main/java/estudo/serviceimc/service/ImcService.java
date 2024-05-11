package estudo.serviceimc.service;

import estudo.serviceimc.dto.ImcData;
import estudo.serviceimc.dto.ImcView;
import estudo.serviceimc.model.Imc;

public class ImcService {

  public Imc fromDto(ImcData imcDto) {
    return new Imc(imcDto.weight(), imcDto.height());
  }

  public ImcView toDto(Imc imc) {
    return new ImcView(imc.getImc(), imc.getMsg(), imc.getDiet());
  }

}
