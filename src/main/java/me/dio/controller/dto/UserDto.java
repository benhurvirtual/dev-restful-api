package me.dio.controller.dto;

import me.dio.domain.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record UserDto(
        Long id,
        String name,
        AccountDto account,
        CardDto card,
        List<FeatureDto> features,
        List<NewsDto> news) {

    public UserDto(User model) {
        this(
                model.getId(),
                model.getName(),
                Optional.ofNullable(model.getAccount()).map(AccountDto::new).orElse(null),
                Optional.ofNullable(model.getCard()).map(CardDto::new).orElse(null),
                model.getFeatures().stream().map(FeatureDto::new).collect(Collectors.toList()),
                model.getNews().stream().map(NewsDto::new).collect(Collectors.toList())
        );
    }

    public User toModel() {
        User model = new User();
        model.setId(this.id);
        model.setName(this.name);
        model.setAccount(Optional.ofNullable(this.account).map(AccountDto::toModel).orElse(null));
        model.setCard(Optional.ofNullable(this.card).map(CardDto::toModel).orElse(null));
        model.setFeatures(this.features != null ? this.features.stream().map(FeatureDto::toModel).collect(Collectors.toList()) : new ArrayList<>());
        model.setNews(this.news != null ? this.news.stream().map(NewsDto::toModel).collect(Collectors.toList()) : new ArrayList<>());
        return model;
    }
}
