package com.example.proyectov2.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectov2.R;
import com.example.proyectov2.databinding.MainPersonaRowBinding;
import com.example.proyectov2.repositories.room.entities.Persona;
import com.example.proyectov2.views.callback.PersonaClickCallback;

import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder> {
    List<? extends Persona> personaList;

    @Nullable
    private final PersonaClickCallback personaClickCallback;

    public PersonaAdapter(@Nullable PersonaClickCallback personaClickCallback) {
        this.personaClickCallback = personaClickCallback;
    }

    public void setPersonaList(final List<? extends Persona> personas) {
        if (this.personaList == null) {
            this.personaList = personas;
            notifyItemRangeInserted(0, personaList.size());
        } else {
            this.personaList = personas;
        }
    }

    @Override
    public PersonaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MainPersonaRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.main_persona_row,parent, false);
        binding.setCallback(personaClickCallback);
        return new PersonaViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PersonaViewHolder holder, int position) {
        holder.binding.setPersonas(personaList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return personaList == null ? 0 : personaList.size();
    }

    public void removeItem(int position) {
        personaList.remove(position);
        notifyItemRemoved(position);
    }

    static class PersonaViewHolder extends RecyclerView.ViewHolder {
        final MainPersonaRowBinding binding;
        public PersonaViewHolder(MainPersonaRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

