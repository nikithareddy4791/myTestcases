 if (dtoCase.getAda() != null) {
            AdaList newAda = adaListRepository.findById(dtoCase.getAda().getId()).orElse(null);
            entityCase.setAda(newAda);
        } else if (entityCase.getAda() != null) {
            entityCase.setAda(null);
        }

        if (dtoCase.getddd() != null) {
            dddOfficeList newddd = dddOfficeListRepository.findById(dtoCase.getddd().getId()).orElse(null);
            entityCase.setddd(newddd);
        } else if (entityCase.getddd() != null) {
            entityCase.setddd(null);
        }

        else if (entityCase.getStatus() != null) {
            entityCase.setStatus(null);
        }

         if (dtoTag.getTagId() == null) continue;
            Optional<CaseTag> foundTag = entityCase.getTags().stream()
                    .filter(entityTag -> entityTag.getTag() != null
                            && dtoTag.getTagId().intValue() == entityTag.getTag().getId())
                    .findFirst();
            if (foundTag.isEmpty()) {
                TagList tag = tagListRepository.findById(dtoTag.getTagId()).orElse(null);
                if (tag != null) {
                    CaseTag newTag = new CaseTag();
                    newTag.setdddCase(entityCase);
                    newTag.setTag(tag);
                    updatedTags.add(newTag);
                } else {
                    log.error("Tag " + dtoTag.getTagId() + " not found for case " + entityCase.getId());
                }
            } else {
                updatedTags.add(foundTag.get());
            }
        } for (org.nnnn.ddd.model.CaseItem dtoItem : dtoItems) {
            Optional<CaseItem> foundItem = Optional.empty();
            if (dtoItem.getId() != null) {
                foundItem = entityCase.getItems().stream()
                        .filter(entityItem -> dtoItem.getId().intValue() == entityItem.getId())
                        .findFirst();
            }
            if (foundItem.isEmpty()) {
                ItemList item = itemListRepository.findById(dtoItem.getItemId()).orElse(null);
                if (item != null) {
                    CaseItem newItem = new CaseItem();
                    newItem.setdddCase(entityCase);
                    newItem.setItem(item);
                    newItem.setNoteDesc(dtoItem.getNoteDesc());
                    newItem.setQuantity(dtoItem.getQuantity());
                    newItem.setStatusDesc(dtoItem.getStatusDesc());
                    updatedItems.add(newItem);
                } else {
                    log.error("Item " + dtoItem.getItemId() + " not found for case " + entityCase.getId());
                }
            } else {
                CaseItem existingItem = foundItem.get();
                existingItem.setNoteDesc(dtoItem.getNoteDesc());
                existingItem.setQuantity(dtoItem.getQuantity());
                existingItem.setStatusDesc(dtoItem.getStatusDesc());
                updatedItems.add(existingItem);
            }
        }

         if (filter.getRequestDtFrom() != null || filter.getRequestDtTo() != null) {
            filters.add(CaseSpecifications.requestBetween(
                    filter.getRequestDtFrom() != null ? LocalDate.parse(filter.getRequestDtFrom()) : null,
                    filter.getRequestDtTo() != null ? LocalDate.parse(filter.getRequestDtTo()) : null));
        }
        if (filter.getDueDtFrom() != null || filter.getDueDtTo() != null) {
            filters.add(CaseSpecifications.dueBetween(
                    filter.getDueDtFrom() != null ? LocalDate.parse(filter.getDueDtFrom()) : null,
                    filter.getDueDtTo() != null ? LocalDate.parse(filter.getDueDtTo()) : null));
        }
        if (filter.getCategoryIds() != null) {
            filters.add(CaseSpecifications.hasCategories(filter.getCategoryIds()));
        }
        if (filter.getdddOfficeIds() != null && filter.getdddOfficeIds().size() > 0) {
            filters.add(CaseSpecifications.hasdddOfficesIn(filter.getdddOfficeIds()));
        }


 if (authenticationService.isSupervisor() && filter.getdddOfficeIds() != null
                && filter.getdddOfficeIds().size() > 0) {
            response.setCaseStats(getCaseStats(filter.getdddOfficeIds()));
        }






         if (filter.getStatusIds() != null && filter.getStatusIds().size() > 0) {
            filters.add(CaseSpecifications.hasStatusesIn(filter.getStatusIds()));
        }
        if (filter.getTagIds() != null && filter.getTagIds().size() > 0) {

            filters.add(CaseSpecifications.hasTagsIn(filter.getTagIds()));
        }
        if (filter.getAssignedNm() != null) {
            filters.add(CaseSpecifications.hasAssignedNm(filter.getAssignedNm()));
        }
        if (filter.getProactiveFlg()!=null) {
            filters.add(CaseSpecifications.hasProactiveFlg(filter.getProactiveFlg().shortValue()));
        } for(dddCaseSummary caseSummary: cases){
            if(arrInfoMap.get(caseSummary.getArrId().trim())!=null){
                caseSummary.setArrest(arrInfoMap.get(caseSummary.getArrId()));
            }
        } 
