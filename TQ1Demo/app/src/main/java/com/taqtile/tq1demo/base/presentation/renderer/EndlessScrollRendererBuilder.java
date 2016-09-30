package com.taqtile.tq1demo.base.presentation.renderer;

import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class EndlessScrollRendererBuilder<T> extends RendererBuilder<T> {

    private final Renderer<T> mWrapperRenderer;
    private Renderer<T> mEndlessScrollRenderer;

    public EndlessScrollRendererBuilder(Renderer<T> wrappedRenderer) {
        mWrapperRenderer = wrappedRenderer;

        Collection<Renderer<T>> prototypes = getEndlessScrollRendererPrototypes();
        setPrototypes((List<Renderer<T>>) prototypes);
    }

    /**
     * Adds a loading proptotype to the RendererBuilder
     * The list of Renderer<Video> that contains all the possible renderers that our RendererBuilder
     * is going to use.
     *
     * @return Renderer<T> prototypes for RendererBuilder.
     */
    private List<Renderer<T>> getEndlessScrollRendererPrototypes() {
        List<Renderer<T>> prototypes = new LinkedList<>();

        mEndlessScrollRenderer = new EndlessScrollRenderer<>();
        prototypes.add(mEndlessScrollRenderer);

        prototypes.add(mWrapperRenderer);

        return prototypes;
    }

    /**
     * This is where we declare that the endless scroll renderer
     * should be the on to be chosen.
     *
     * @param content used to map object-renderers.
     * @return Renderer<T> class.
     */
    @Override
    protected Class getPrototypeClass(T content) {
        if (content != null) {
            return getPrototypes().get(1).getClass();
        } else {
            return EndlessScrollRenderer.class;
        }
    }
}
